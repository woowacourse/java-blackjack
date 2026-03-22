package domain.participant;

import domain.Money;

public class Player extends Participant {
    private Money money;

    public Player(Name name, Money money) {
        super(name);
        this.money = money;
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
