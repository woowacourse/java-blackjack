package blackjack.domain.user;

import java.util.Objects;

public class Money {
    private final int money;

    public Money(int money) {
        this.money = money;
    }

    public Money() {
        this(0);
    }

    public Money multiply(double rate) {
        return new Money((int) (this.money * rate));
    }

    public Money add(int money) {
        return new Money(this.money + money);
    }

    public Money add(Money money) {
        return add(money.money);
    }

    public Money subtract(int money) {
        return new Money(this.money - money);
    }

    public Money subtract(Money money) {
        return subtract(money.money);
    }

    public int getMoney() {
        return this.money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money1 = (Money) o;
        return money == money1.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }

    @Override
    public String toString() {
        return Integer.toString(money);
    }
}
