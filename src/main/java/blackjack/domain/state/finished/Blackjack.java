package blackjack.domain.state.finished;

import blackjack.domain.cards.Cards;

public final class Blackjack extends Finished {

    public Blackjack(final Cards cards) {
        super(cards);
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public boolean isBlackjack() {
        return true;
    }

    @Override
    public double earningRate(final Finished state) {
        if (state.isBlackjack()) {
            return TIE_RATE;
        }
        return BLACKJACK_RATE;
    }
}
