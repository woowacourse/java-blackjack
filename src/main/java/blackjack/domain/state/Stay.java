package blackjack.domain.state;

import blackjack.domain.card.HoldCards;
import blackjack.domain.entry.Dealer;

public final class Stay extends Finished {

    private static final double STAY_RATE = 1;
    private static final double LOSE_RATE = -1;

    Stay(HoldCards holdCards) {
        super(holdCards);
    }

    @Override
    protected double earningRate(Dealer dealer) {
        if (dealer.isBust()) {
            return STAY_RATE;
        }
        if (dealer.countCards() >= getHoldCards().countBestNumber()) {
            return LOSE_RATE;
        }
        return STAY_RATE;
    }

}
