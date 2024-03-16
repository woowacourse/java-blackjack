package domain.state.fininsh;

import domain.card.Hands;

public final class Bust extends Finished {
    public Bust(final Hands hands) {
        super(hands);
    }

    @Override
    public boolean isBust() {
        return true;
    }
}
