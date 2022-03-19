package blackjack_statepattern.state;

import blackjack_statepattern.card.Cards;

public final class Stay extends Finished {
    Stay(final Cards cards) {
        super(cards);
    }

    @Override
    protected double earningRate(Cards comparedCards) {
        if (comparedCards.isBlackjack() || comparedCards.computeScore() > cards().computeScore()) {
            return -1;
        }
        if (comparedCards.computeScore() == cards().computeScore()) {
            return 0;
        }
        return 1;
    }
}
