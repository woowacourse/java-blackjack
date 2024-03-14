package domain.state;

import domain.player.Hands;

public abstract class Started implements State {
    private final Hands hands;

    public Started(final Hands hands) {
        this.hands = hands;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    public Hands getHands() {
        return hands;
    }
}
