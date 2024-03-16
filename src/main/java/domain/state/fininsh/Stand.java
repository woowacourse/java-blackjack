package domain.state.fininsh;

import domain.card.Hands;

public final class Stand extends Finished {
    public Stand(final Hands hands) {
        super(hands);
    }

    @Override
    public boolean isStand() {
        return true;
    }
}
