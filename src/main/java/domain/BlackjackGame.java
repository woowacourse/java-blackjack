package domain;

import static constant.Word.*;

import domain.dto.GameResult;
import domain.card.Card;
import domain.dto.MemberStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import presentation.ui.InputView;
import presentation.ui.OutputView;

public class BlackjackGame {

    private final GameTable gameTable;
    private final Deck deck;

    public BlackjackGame() {
        this.gameTable = new GameTable();
        this.deck = new Deck();
        this.deck.init();
    }

    public void playGame(List<String> playerNames, InputView inputView, OutputView outputView) {
        for (String playerName : playerNames) {
            playAllRoundOfPlayer(playerName, inputView, outputView);
        }
    }

    public void joinPlayerToGame(List<String> players) {
        gameTable.joinPlayer(players);
        List<String> allParticipants = Stream.concat(
                Stream.of(DEALER.format()),
                players.stream()
        ).toList();

        allParticipants.forEach(name -> {
            gameTable.drawByName(name, deck.draw());
            gameTable.drawByName(name, deck.draw());
        });
    }

    public boolean checkDealerDrawable() {
        return gameTable.dealerDrawable();

    }

    public void dealerDrawCard() {
        gameTable.drawDealer(deck.draw());
    }

    public List<MemberStatus> playerHands() {
        MemberStatus dealer = checkDealerStatus();
        List<MemberStatus> players = checkPlayerStatuses();

        List<MemberStatus> totalStatuses = new ArrayList<>();
        totalStatuses.add(dealer);
        totalStatuses.addAll(players);

        return totalStatuses;
    }

    public GameResult getGameResults() {
        Map<String, RoundResult> gameResults = gameTable.checkGameResult();
        int dealerLoseAmount = Math.toIntExact(gameResults.values().stream()
                .filter(result -> result.equals(RoundResult.WIN))
                .count());
        int dealerWinAmount = Math.toIntExact(gameResults.values().stream()
                .filter(result -> result.equals(RoundResult.LOSE))
                .count());
        return new GameResult(dealerWinAmount, dealerLoseAmount, gameResults);
    }

    private List<MemberStatus> checkPlayerStatuses() {
        return gameTable.allPlayers()
                .stream()
                .map(name -> {
                    List<Card> cards = gameTable.playerCards(name);
                    int playerPoint = gameTable.playerPoint(name);
                    return new MemberStatus(name, cards, playerPoint);
                }).collect(Collectors.toList());
    }

    private MemberStatus checkDealerStatus() {
        List<Card> cards = gameTable.dealerCards();
        int dealerPoint = gameTable.dealerPoint();
        return new MemberStatus(DEALER.format(), cards, dealerPoint);
    }

    private void playAllRoundOfPlayer(String playerName, InputView inputView, OutputView outputView) {
        while (isContinuable(playerName) && inputView.playContinue(playerName)) {
            List<Card> cards = gameTable.drawByName(playerName, deck.draw());
            outputView.printCurrentCard(playerName, cards);
        }
    }

    private boolean isContinuable(String playerName) {
        return !gameTable.checkBust(playerName);
    }
}
