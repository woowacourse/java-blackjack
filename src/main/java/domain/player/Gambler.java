package domain.player;

import domain.betting.BettingAmount;
import domain.card.GameCards;

public class Gambler extends Participant {

    private final BettingAmount bettingAmount;

    public Gambler(String name, BettingAmount bettingAmount) {
        super(name);
        this.bettingAmount = bettingAmount;
    }

    public void receiveInitialCards(GameCards gameCards) {
        for (int i = 0; i < DEFAULT_START_CARD_COUNT; i++) {
            this.addCard(gameCards.drawCard());
        }
    }

    public BettingAmount getBettingAmount() {
        return bettingAmount;
    }
}
