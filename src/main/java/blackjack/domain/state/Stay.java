package blackjack.domain.state;

import blackjack.domain.card.HoldCards;

public final class Stay extends Finished {

    Stay(HoldCards holdCards) {
        super(holdCards);
    }

    @Override
    protected double earningRate() {
        return 1;
    }

}
