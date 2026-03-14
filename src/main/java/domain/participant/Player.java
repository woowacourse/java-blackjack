package domain.participant;

import domain.betting.Money;

public class Player extends Participant {

    private Money money;

    public Player(String name) {
        super(name);
    }

    public void bettingMoney(Money money) {
        this.money = money;
    }

    public Money getBettingMoney() {
        return money;
    }
}
