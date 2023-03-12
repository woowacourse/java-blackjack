package domain.user;

import domain.state.UserReady;

public class Player extends User {
    public Player(Name name) {
        super(new UserData(name, new UserReady()));
    }
}
