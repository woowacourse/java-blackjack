package domain.game;

import static util.Constants.DEFAULT_START_CARD_COUNT;

import domain.card.GameCards;
import domain.player.Dealer;
import domain.player.Gamblers;
import java.util.List;
import java.util.Map;


public class Game {

    private final Dealer dealer;
    private final Gamblers gamblers;
    private final GameCards gameCards;

    public Game(String dealerName, List<String> names, int amount) {
        this.dealer = new Dealer(dealerName);
        this.gamblers = new Gamblers(names);
        this.gameCards = new GameCards(amount);
    }

    public void initializeGame() {
        for (int i = 0; i < DEFAULT_START_CARD_COUNT; i++) {
            dealer.addCard(gameCards.drawCard());
            gamblers.receiveCards(gameCards);
        }
    }

    public String getDealerName() {
        return dealer.getName();
    }

    public String getDealerInitialInfo() {
        return dealer.getFirstHand();
    }

    public List<String> getDealerHandInfo() {
        return dealer.getHandInfo();
    }

    public Map<String, List<String>> getGamblersHandInfo() {
        return gamblers.getHandsInfo();
    }

    public int getDealerHandSize() {
        return dealer.getCardSize();
    }

    public List<Integer> getGamblersHandSize() {
        return gamblers.getHandSize();
    }
}

