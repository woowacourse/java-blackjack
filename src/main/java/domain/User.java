package domain;

import java.math.BigDecimal;
import vo.Bet;
import vo.Name;

public class User extends Participant {
    private final Name name;
    private final Bet bet;

    public User(Name name, Bet bet) {
        this.name = name;
        this.bet = bet;
    }

    public BigDecimal getBetAmount() {
        return bet.getAmount();
    }

    public String getName() {
        return name.getName();
    }
}
