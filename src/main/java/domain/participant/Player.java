package domain.participant;

import domain.bet.Money;

public class Player extends Participant {

    private Money bet;

    public Player(Name name) {
        super(name);
    }

    public void placeBet(Money money) {
        this.bet = money;
    }

    public int getBetAmount() {
        return bet.value();
    }
}
