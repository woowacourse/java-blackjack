package blackjack.domain.state;

import blackjack.domain.card.HoldCards;

public final class Stay extends Finished {

    private static final double STAY_RATE = 1;

    Stay(HoldCards holdCards) {
        super(holdCards);
    }

    @Override
    protected double earningRate() {
        return STAY_RATE;
    }

}
