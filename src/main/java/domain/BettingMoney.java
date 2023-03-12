package domain;

import java.util.Objects;

public class BettingMoney {
    private int money;

    public BettingMoney(int money) {
        this.money = money;
    }

    public int getBettingMoney() {
        return money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BettingMoney that = (BettingMoney) o;
        return money == that.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
