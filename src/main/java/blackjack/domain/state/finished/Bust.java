package blackjack.domain.state.finished;

import blackjack.domain.cards.Cards;

public final class Bust extends Finished {

    public Bust(final Cards cards) {
        super(cards);
    }

    @Override
    public boolean isBust() {
        return true;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public double earningRate(final Finished other) {
        if (other.isBust()) {
            return WIN_PROFIT_RATE;
        }
        return LOSE_PROFIT_RATE;
    }
}
