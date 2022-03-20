package blackjack.domain.state.finished;

import blackjack.domain.cards.Cards;

public final class Bust extends Finished {

    public Bust(final Cards cards) {
        super(cards);
    }

    @Override
    public double computedRate(final Finished state) {
        if (state.earningRate() == LOSE_RATE) {
            return WIN_RATE;
        }
        return LOSE_RATE;
    }

    @Override
    public double earningRate() {
        return LOSE_RATE;
    }
}
