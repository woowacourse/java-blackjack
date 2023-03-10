package domain.participant;

import util.Constants;

public class Dealer extends Participant {

    private static final int LIMIT_HIT_VALUE = 17;


    public Dealer(HandCards handCards) {
        super(new Name(Constants.DEALER_NAME), handCards);
    }

    public boolean canHit() {
        return handCards.calculateOptimalCardValueSum() < LIMIT_HIT_VALUE;
    }
}
