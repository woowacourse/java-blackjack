package blackjack_statepattern.state;

import blackjack_statepattern.card.Cards;

public final class Stay extends Finished {
    Stay(final Cards cards) {
        super(cards);
    }

    @Override
    protected double earningRate(Cards cards) {
        if (cards.isBlackjack() || cards.computeScore() < cards().computeScore()) {
            return -1;
        }
        return 1;
    }
}
