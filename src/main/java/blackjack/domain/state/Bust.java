package blackjack.domain.state;

import blackjack.domain.card.HoldCards;

public final class Bust extends Finished {

    private static final double BUST_RATE = -1;

    Bust(HoldCards holdCards) {
        super(holdCards);
    }

    @Override
    protected double earningRate() {
        return BUST_RATE;
    }
}
