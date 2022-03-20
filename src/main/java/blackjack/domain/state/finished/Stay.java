package blackjack.domain.state.finished;

import blackjack.domain.cards.Cards;

public final class Stay extends Finished {

    public Stay(final Cards cards) {
        super(cards);
    }

    @Override
    public double computedRate(final Finished state) {
        if (state.earningRate() == LOSE_RATE || cards.hasMorePoint(state.cards())) {
            return WIN_RATE;
        }
        if (cards.hasSamePoint(state.cards())) {
            return TIE_RATE;
        }
        return LOSE_RATE;
    }

    @Override
    public double earningRate() {
        return 1.0;
    }
}
