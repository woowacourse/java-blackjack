package domain.betting;

import domain.game.Money;

import java.math.BigDecimal;

public class BettingLog {
    private final String name;
    private final Money money;

    public BettingLog(String name, Money money) {
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getMoney() {
        return money.getMoney();
    }
}
