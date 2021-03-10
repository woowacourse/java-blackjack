package blackjack.domain;

import java.util.Objects;

public class Money {

    private final double money;

    public Money(double money) {
        this.money = money;
    }

    public Money multiply(double earningRate) {
        return new Money(money * earningRate);
    }

    public Money sum(Money that) {
        return new Money(money + that.money);
    }

    public int getMoney() {
        return (int) money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money1 = (Money) o;
        return money == money1.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
