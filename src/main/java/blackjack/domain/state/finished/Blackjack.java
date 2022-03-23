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
    public double earningRate(final Finished other) {
        if (other.isBlackjack()) {
            return PUSH_PROFIT_RATE;
        }
        return BLACKJACK_PROFIT_RATE;
    }
}
