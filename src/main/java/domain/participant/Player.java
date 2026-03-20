package domain.participant;

import domain.Money;

public class Player extends Participant {
    private Money betAmount;

    public Player(String name) {
        super(name);
        this.betAmount = new Money(0);
    }

    public void setBetAmount(int betAmount) {
        this.betAmount = new Money(betAmount);
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
