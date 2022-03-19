package blackjack.domain.state;

import blackjack.domain.card.HoldCards;

public final class Blackjack extends Finished {

    Blackjack(HoldCards holdCards) {
        super(holdCards);
    }

    @Override
    public double earningRate() {
        return 1.5;
    }

}
