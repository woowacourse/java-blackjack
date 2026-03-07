package domain.game;

import static util.Constants.DEALER_NAME;
import static util.Constants.DEFAULT_START_CARD_COUNT;

import domain.card.GameCards;
import domain.player.Dealer;
import domain.player.Gamblers;
import java.util.List;


public class Game {
    private final Dealer dealer;
    private final Gamblers gamblers;
    private final GameCards gameCards;

    public Game(List<String> names, int amount) {
        this.dealer = new Dealer(DEALER_NAME);
        this.gamblers = new Gamblers(names);
        this.gameCards = new GameCards(amount);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Gamblers getGamblers() {
        return gamblers;
    }

    public void initializeGame() {
        for (int i = 0; i < DEFAULT_START_CARD_COUNT; i++) {
            dealer.addCard(gameCards.drawCard());
            gamblers.receiveCards(gameCards);
        }
    }
}

