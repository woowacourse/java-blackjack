package application;

import static constant.Word.*;

import application.dto.GameResult;
import domain.RoundResult;
import domain.Deck;
import domain.GameTable;
import domain.card.Card;
import domain.dto.MemberStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class BlackjackService {

    private final GameTable gameTable;
    private final Deck deck;

    public BlackjackService(GameTable gameTable, Deck deck) {
        this.gameTable = gameTable;
        this.deck = deck;
        this.deck.init();
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

    public List<Card> startOneRound(String memberName) {
        return gameTable.drawByName(memberName, deck.draw());
    }

    public boolean drawDealerCardIfAvailable() {
        return gameTable.drawByDealer(deck.draw());
    }

    public List<MemberStatus> getMemberStatuses() {
        MemberStatus dealer = gameTable.checkDealerStatus();
        List<MemberStatus> players = gameTable.checkPlayerStatuses();

        List<MemberStatus> totalStatuses = new ArrayList<>();
        totalStatuses.add(dealer);
        totalStatuses.addAll(players);

        return totalStatuses;
    }

    public boolean isContinuable(String playerName) {
        return !gameTable.checkBust(playerName);
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
}
