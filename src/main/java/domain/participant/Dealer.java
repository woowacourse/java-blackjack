package domain.participant;

import domain.card.Hand;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int STAY_LOWER_BOUND = 16;

    private Dealer(Hand hand) {
        super(DEALER_NAME, hand);
    }

    public static Dealer from(Hand hand) {
        return new Dealer(hand);
    }

    @Override
    public boolean isStand() {
        return calculateScore() > STAY_LOWER_BOUND;
    }

    @Override
    public void stand() {
        throw new UnsupportedOperationException();
    }
}
