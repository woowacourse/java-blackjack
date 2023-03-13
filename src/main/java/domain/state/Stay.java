package domain.state;

import domain.card.Hand;

public class Stay extends Finished {

    private static final int PROFIT_RATE = 1;

    Stay(final Hand hand) {
        super(hand);
    }

    @Override
    double profitRate() {
        return PROFIT_RATE;
    }

    @Override
    public boolean isStay() {
        return true;
    }
}
