package domain.state.fininsh;

import domain.player.Hands;

public class Stand extends Finished {
    public Stand(final Hands hands) {
        super(hands);
    }

    @Override
    public double earningRate() {
        return 0;
    }
}
