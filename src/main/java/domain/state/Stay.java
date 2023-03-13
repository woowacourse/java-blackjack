package domain.state;

import domain.user.Hand;

public class Stay extends Finished {

    Stay(Hand hand) {
        super(hand);
    }

    @Override
    double profitRate() {
        return 1;
    }

    @Override
    public boolean isStay() {
        return true;
    }
}
