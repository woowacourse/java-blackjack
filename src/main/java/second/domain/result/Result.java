package second.domain.result;

import second.domain.gamer.Money;

public class Result {
    private final String name;
    private final Money money;

    public Result(String name, Money money) {
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public Money getMoney() {
        return money;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", name, money.getValue());
    }
}
