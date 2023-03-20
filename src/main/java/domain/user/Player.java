package domain.user;

import domain.state.UserReady;

public final class Player extends User {
    private final int betting;

    public Player(Name name, int betting) {
        super(new UserData(name, new UserReady()));
        this.betting = betting;
    }

    @Override
    public int getPrize() {
        return getUserData().getPrize(betting);
    }

    public int getBetting() {
        return betting;
    }
}
