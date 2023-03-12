package domain.user;

import domain.state.DealerReady;

public class Dealer extends User {
    public Dealer() {
        super(new UserData(new Name("딜러"), new DealerReady()));
    }
}
