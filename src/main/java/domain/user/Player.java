package domain.user;

import domain.state.UserReady;

public final class Player extends User {
    public Player(String name) {
        super(new Name(name), new UserReady());
    }

    @Override
    public double getProfitRatio() {
        return getState().getProfitRatio();
    }
}
