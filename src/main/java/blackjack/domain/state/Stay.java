package blackjack.domain.state;

import blackjack.domain.card.Cards;

public final class Stay extends Finished {

    Stay(final Cards cards) {
        super(cards);
    }

    @Override
    protected double earningRate(State compareState) {
        if (compareState instanceof BlackJack) {
            return -1;
        }
        if (compareState instanceof Bust) {
            return 1;
        }
        if (compareState.cards().sum() < this.cards().sum()) {
            return 1;
        }
        return -1;
    }
}
