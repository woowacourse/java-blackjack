package domain.state.fininsh;

import domain.card.Hands;

public final class Blackjack extends Finished {
    public Blackjack(final Hands hands) {
        super(hands);
    }

    @Override
    public boolean isBlackjack() {
        return true;
    }

    @Override
    public double earningRate() {
        return 1.5;
    }
}
