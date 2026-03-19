package domain.participant;

import domain.bet.Money;

public class Player extends Participant {

    private final Money bet;

    public Player(Name name, Money bet) {
        super(name);
        this.bet = bet;
    }

    public int getBetAmount() {
        return bet.value();
    }
}
