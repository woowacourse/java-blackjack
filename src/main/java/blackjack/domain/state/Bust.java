package blackjack.domain.state;

import blackjack.domain.card.HoldCards;
import blackjack.domain.entry.Dealer;

public final class Bust extends Finished {

    private static final double BUST_RATE = -1;

    Bust(HoldCards holdCards) {
        super(holdCards);
    }

    @Override
    protected double earningRate(Dealer dealer) {
        return BUST_RATE;
    }
}
