package domain;

import domain.table.GameTable;
import domain.card.Deck;
import java.util.List;
import java.util.Map;

public class BlackjackGame {

    private final GameTable gameTable;
    private final Deck deck;

    public BlackjackGame(Map<String, Integer> players) {
        this.gameTable = new GameTable(players);
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

    public List<String> getCardNames(String memberName) {
        return gameTable.getCards(memberName).stream()
                .map(card -> card.getCourt() + card.getPattern())
                .toList();
    }

    public List<String> getFirstCardNames(String memberName) {
        return gameTable.getFirstCards(memberName).stream()
                .map(card -> card.getCourt() + card.getPattern())
                .toList();
    }

    public boolean canDealerDraw() {
        return gameTable.canDealerDraw();
    }

    public void drawDealer() {
        gameTable.draw(getDealerName(), deck.draw());
    }

    public List<String> getMemberNames() {
        return gameTable.getMemberNames();
    }

    public int getMemberPoint(String memberName) {
        return gameTable.memberPoint(memberName);
    }

    public Map<String, Integer> getGameResults() {
        return gameTable.checkGameResult();
    }

    public boolean isContinuable(String playerName) {
        return !gameTable.checkBust(playerName);
    }

    public boolean hasBlackjack() {
        return gameTable.checkBlackjack();
    }

    public void applyBlackjackBonus() {
        gameTable.applyBlackjackBonus();
    }
}
