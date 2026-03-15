package blackjack.domain.participant;

import blackjack.domain.BettingAmount;
import java.math.BigDecimal;

public class User extends Participant {

    private final BettingAmount bettingAmount;

    public User(String name, BettingAmount bettingAmount) {
        super(name);
        this.bettingAmount = bettingAmount;
    }

    public BigDecimal getBettingAmount() {
        return bettingAmount.getAmount();
    }
}
