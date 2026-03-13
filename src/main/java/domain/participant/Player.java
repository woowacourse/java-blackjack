package domain.participant;

import domain.bet.Money;

public class Player extends Participant {

    private Money betAmount;

    public Player(Name name) {
        super(name);
    }

    public void placeBet(Money betAmount) {
        this.betAmount = betAmount;
    }

    public int getBetAmount() {
        return betAmount.value();
    }
}
