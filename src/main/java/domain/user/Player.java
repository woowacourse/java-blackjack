package domain.user;

import domain.state.UserReady;

public final class Player extends User {
//    private final int betting;

//    public Player(Name name, int betting) {
//        super(name, new UserReady());
//        this.betting = betting;
//    }

    public Player(String name) {
        super(new Name(name), new UserReady());
    }

//    @Override
//    public int getPrize() {
//        return getPrize(betting);
//    }

    @Override
    public double getProfitRatio() {
        return getState().getProfitRatio();
    }

//    public int getBetting() {
//        return betting;
//    }
}
