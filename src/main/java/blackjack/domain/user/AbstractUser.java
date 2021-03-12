package blackjack.domain.user;

import blackjack.domain.state.State;

public abstract class AbstractUser {
    private State state;

    protected AbstractUser(State state) {
        this.state = state;
    }

    public final void changeState(State state) {
        this.state = state;
    }

    public final State getState() {
        return state;
    }

    public abstract String getName();

    public abstract boolean canDraw();
}
