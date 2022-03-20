package blackjack.domain.state.finished;

import blackjack.domain.cards.Cards;

public final class Blackjack extends Finished {

    public Blackjack(final Cards cards) {
        super(cards);
    }

    @Override
    public double computedRate(final Finished state) {
        if (state.earningRate() == BLACKJACK_RATE) {
            return TIE_RATE;
        }
        return BLACKJACK_RATE;
    }

    @Override
    public double earningRate() {
        return BLACKJACK_RATE;
    }
}
