package domain.state.fininsh;

import domain.state.Hands;

public final class Stand extends Finished {
    public Stand(final Hands hands) {
        super(hands);
    }

    @Override
    public double earningRate() {
        return 0;
    }
}
