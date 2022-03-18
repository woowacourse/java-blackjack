package blackjack.domain.state;

import blackjack.domain.card.Cards;

public final class Stay extends Finished {

    Stay(final Cards cards) {
        super(cards);
    }

    @Override
    protected double earningRate() {
        return 1;
    }

}
