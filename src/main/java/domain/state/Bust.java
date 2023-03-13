package domain.state;

import domain.user.Hand;

public class Bust extends Finished {

    Bust(Hand hand) {
        super(hand);
    }

    @Override
    double profitRate() {
        return -1;
    }

    @Override
    public boolean isStay() {
        return false;
    }
}
