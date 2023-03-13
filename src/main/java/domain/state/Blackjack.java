package domain.state;

import domain.user.Hand;

public class Blackjack extends Finished {

    Blackjack(Hand hand) {
        super(hand);
    }

    @Override
    double profitRate() {
        return 1.5;
    }

    @Override
    public boolean isStay() {
        return false;
    }
}
