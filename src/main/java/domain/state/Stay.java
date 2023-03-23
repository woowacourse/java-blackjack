package domain.state;

import domain.card.Hand;

public final class Stay extends Finished {

    public static final int STAY_PROFIT_RATIO = 1;

    Stay(Hand hand) {
        super(hand);
    }

    @Override
    public double getProfitRatio() {
        return STAY_PROFIT_RATIO;
    }
}
