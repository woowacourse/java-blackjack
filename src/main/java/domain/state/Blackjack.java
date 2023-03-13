package domain.state;

import domain.user.Hand;

public class Blackjack extends Finished {

    private static final double PROFIT_RATE = 1.5;

    Blackjack(Hand hand) {
        super(hand);
    }

    @Override
    double profitRate() {
        return PROFIT_RATE;
    }

    @Override
    public boolean isStay() {
        return false;
    }
}
