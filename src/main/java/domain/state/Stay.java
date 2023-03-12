package domain.state;

import domain.user.Hand;

public class Stay extends Finished {

    public Stay(Hand hand) {
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
