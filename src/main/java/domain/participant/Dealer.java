package domain.participant;

import domain.card.Card;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int STAY_LOWER_BOUND = 16;
    private static final int VISIBLE_CARD_INDEX = 0;

    public Dealer() {
        super(DEALER_NAME);
    }

    public boolean canHit() {
        return calculateScore() <= STAY_LOWER_BOUND;
    }

    public Card getCardWithInvisible() {
        return getCards().get(VISIBLE_CARD_INDEX);
    }
}
