package domain.participant;

import domain.betting.Money;

public final class Player extends Participant {

    private final String name;
    private Money money;

    public Player(String name) {
        this.name = name;
    }

    public void bettingMoney(Money money) {
        this.money = money;
    }

    public Money getBettingMoney() {
        return money;
    }

    public String getName() {
        return name;
    }
}
