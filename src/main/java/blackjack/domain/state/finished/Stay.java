package blackjack.domain.state.finished;

import blackjack.domain.cards.Cards;

public final class Stay extends Finished {

    public Stay(final Cards cards) {
        super(cards);
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public double earningRate(final Finished state) {
        if (state.isBust() || cards.hasMorePoint(state.cards())) {
            return WIN_RATE;
        }
        if (cards.hasSamePoint(state.cards())) {
            return TIE_RATE;
        }
        return LOSE_RATE;
    }
}
