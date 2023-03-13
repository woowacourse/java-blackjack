package domain.participant;

import util.Constants;

public class Dealer extends Participant {

    private static final int LIMIT_HIT_VALUE = 17;


    public Dealer(Hand hand) {
        super(new Name(Constants.DEALER_NAME), hand);
    }

    public boolean canHit() {
        return this.calculateOptimalCardValueSum() < LIMIT_HIT_VALUE;
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }
}
