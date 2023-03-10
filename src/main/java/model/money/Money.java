package model.money;

import java.util.Objects;

public class Money {

    private static final Money zero = new Money(0);

    private final long money;

    public Money(final long money) {
        this.money = money;
    }

    public static Money zero() {
        return zero;
    }

    public Money add(final Money money) {
        return new Money(this.money + money.money);
    }

    public Money lose() {
        return new Money(-money);
    }

    public Money blackJack() {
        return new Money((long) (money * 1.5));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Money money1 = (Money) o;
        return Objects.equals(money, money1.money);
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }

    @Override
    public String toString() {
        return "Money{" +
                "money=" + money +
                '}';
    }
}
