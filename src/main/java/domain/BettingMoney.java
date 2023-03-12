package domain;

import java.util.Objects;

public class BettingMoney {
    private static final Double BLACK_JACK_BONUS = 1.5;
    private static final Integer LOSE_MONEY = -1;

    private int money;

    public BettingMoney(int money) {
        this.money = money;
    }

    public int bust() {
        this.money = this.money * LOSE_MONEY;
        return this.money;
    }

    public int blackJack() {
        this.money = (int) (this.money * BLACK_JACK_BONUS);
        return this.money;
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
