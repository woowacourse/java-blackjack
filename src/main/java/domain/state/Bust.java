package domain.state;

import domain.card.Hand;

public final class Bust extends Finished {
    public static final int BUST_PROFIT_RATIO = -1;

    Bust(Hand hand) {
        super(hand);
    }

    @Override
    public double getProfitRatio() {
        return BUST_PROFIT_RATIO;
    }
}
