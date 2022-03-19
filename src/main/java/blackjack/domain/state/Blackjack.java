package blackjack.domain.state;

import blackjack.domain.card.HoldCards;

public final class Blackjack extends Finished {

    private static final double BLACKJACK_RATE = 1.5;

    Blackjack(HoldCards holdCards) {
        super(holdCards);
    }

    @Override
    public double earningRate() {
        return BLACKJACK_RATE;
    }

}
