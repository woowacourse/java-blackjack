package domain.participant;

import domain.card.Card;

public class Dealer extends Participant {
    private static final int DEALER_MAX_HITTABLE_SCORE = 17;
    private static final String DEALER_NAME = "딜러";

    public Dealer(Hand hand) {
        super(DEALER_NAME, hand);
    }

    @Override
    public boolean canHit() {
        return getTotalCardScore() < DEALER_MAX_HITTABLE_SCORE;
    }

    @Override
    public void keepCard(Card card) {
        if (canHit()) {
            this.getHand().addCard(card);
        }
    }
}
