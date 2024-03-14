package domain.state.fininsh;

import domain.player.Hands;

public class Bust extends Finished {
    public Bust(final Hands hands) {
        super(hands);
    }

    @Override
    public double earningRate() {
        return 0;
    }
}
