package domain;

import vo.Bet;
import vo.Name;

public class User extends Participant {
    private final Name name;
    private Bet bet;

    public User(String name) {
        this.name = new Name(name);
    }

    public void placeBet(String betAmount) {
        this.bet = new Bet(betAmount);
    }

    public String getName() {
        return name.getName();
    }
}