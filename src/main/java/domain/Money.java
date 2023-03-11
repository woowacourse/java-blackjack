package domain;

import java.util.Objects;

public class Money {
    private final static Money ZERO = new Money(0);

    private final int amount;

    public Money(int amount) {
        this.amount = amount;
    }

    public static Money zero() {
        return ZERO;
    }

    public Money add(Money other) {
        return new Money(this.amount + other.amount);
    }

    public Money subtract(Money other) {
        return new Money(this.amount - other.amount);
    }

    public Money multiply(double times) {
        return new Money((int) (this.amount * times));
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
