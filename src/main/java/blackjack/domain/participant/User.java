package blackjack.domain.participant;

import blackjack.domain.BettingAmount;

public class User extends Participant {

    private final BettingAmount bettingAmount;

    public User(String name, BettingAmount bettingAmount) {
        super(name);
        this.bettingAmount = bettingAmount;
    }

    public int getBettingAmount() {
        return bettingAmount.getAmount();
    }

}
