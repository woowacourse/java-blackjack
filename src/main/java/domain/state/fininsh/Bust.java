package domain.state.fininsh;

import domain.state.Hands;

public final class Bust extends Finished {
    public Bust(final Hands hands) {
        super(hands);
    }

    @Override
    public boolean isBust() {
        return true;
    }

    @Override
    public double earningRate() {
        return 0;
    }
}
