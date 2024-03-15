package domain.state.fininsh;

import domain.state.Hands;

public final class Blackjack extends Finished {
    public Blackjack(final Hands hands) {
        super(hands);
    }

    @Override
    public double earningRate() {
        return 0;
    }
}
