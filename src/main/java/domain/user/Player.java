package domain.user;

import domain.state.UserReady;

public final class Player extends User {
    private int batting;

    public Player(Name name, int batting) {
        super(new UserData(name, new UserReady()));
        this.batting = batting;
    }

    public int getPrize() {
        return getUserData().getPrize(batting);
    }

    public int getBatting() {
        return batting;
    }
}
