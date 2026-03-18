package domain.player;

import domain.card.GameCards;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_REFERENCE_POINT = 16;

    public Dealer() {
        super(DEALER_NAME);
    }

    public boolean isBelowDrawThreshold() {
        return getTotalScore() <= DEALER_REFERENCE_POINT;
    }

    public String getFirstHand() {
        return hand.getFirstCardInfo();
    }

    public void receiveInitialCards(GameCards gameCards) {
        for (int i = 0; i < DEFAULT_START_CARD_COUNT; i++) {
            this.addCard(gameCards.drawCard());
        }
    }
}
