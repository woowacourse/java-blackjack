package blackjack.domain.state;

import blackjack.domain.card.HoldCards;

public final class Bust extends Finished {

    Bust(HoldCards holdCards) {
       super(holdCards);
    }

    @Override
    protected double earningRate() {
        return -1;
    }
}
