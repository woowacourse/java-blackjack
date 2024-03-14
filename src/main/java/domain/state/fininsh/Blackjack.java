package domain.state.fininsh;

import domain.player.Hands;

public class Blackjack extends Finished {
    public Blackjack(final Hands hands) {
        super(hands);
    }

    @Override
    public double earningRate() {
        return 0;
    }
}
