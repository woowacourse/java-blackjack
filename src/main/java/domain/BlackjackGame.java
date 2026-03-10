package domain;

import domain.table.GameTable;
import domain.vo.RoundResult;
import domain.card.Deck;
import presentation.dto.GameResult;
import domain.card.Card;
import presentation.dto.MemberStatus;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class BlackjackGame {

    private final GameTable gameTable;
    private final Deck deck;

    public BlackjackGame(List<String> playerNames) {
        this.gameTable = new GameTable(playerNames);
        this.deck = new Deck();
        this.deck.init();
    }

    public String getDealerName() {
        return gameTable.getDealerName();
    }

    public void initialDeal() {
        gameTable.getMemberNames().forEach(name -> {
                gameTable.draw(name, deck.draw());
                gameTable.draw(name, deck.draw());
            }
        );
    }

    public void drawPlayer(String playerName) {
        gameTable.draw(playerName, deck.draw());
    }

    public List<String> getCardNames(String playerName) {
        return gameTable.getCards(playerName).stream()
                .map(Card::getCardName)
                .toList();
    }

    public boolean canDealerDraw() {
        return gameTable.canDealerDraw();
    }

    public void drawDealer() {
        gameTable.draw(getDealerName(), deck.draw());
    }

    public List<MemberStatus> memberFirstHands() {
        return gameTable.getMemberNames()
                .stream()
                .map(name -> {
                    List<Card> cards = gameTable.getFirstCards(name);
                    int memberPoint = gameTable.memberPoint(name);
                    return new MemberStatus(name, cards, memberPoint);
                }).toList();
    }

    public List<MemberStatus> memberHands() {
        return gameTable.getMemberNames()
                .stream()
                .map(name -> {
                    List<Card> cards = gameTable.getCards(name);
                    int playerPoint = gameTable.memberPoint(name);
                    return new MemberStatus(name, cards, playerPoint);
                }).toList();
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

    public boolean isContinuable(String playerName) {
        return !gameTable.checkBust(playerName);
    }
}
