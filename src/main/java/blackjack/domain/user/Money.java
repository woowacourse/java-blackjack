package blackjack.domain.user;

import java.util.Objects;

public class Money {
    private double money;

    public Money(double money) {
        this.money = money;
    }

    protected Money multiplyByRate(double earningRate) {
        return new Money(money * earningRate);
    }

    public Money addProfit(double money) {
        return new Money(this.money + money);
    }

    public double getMoney() {
        return this.money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money1 = (Money) o;
        return Double.compare(money1.money, money) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
