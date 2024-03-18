package domain.user;

import domain.money.Money;

public class Player extends User {
    private final Money money;

    public Player(Name name, Money money) {
        super(name);
        this.money = money;
    }

    public Money getMoney() {
        return money;
    }
}
