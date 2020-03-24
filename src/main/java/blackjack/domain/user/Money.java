package blackjack.domain.user;

import java.util.Objects;

public class Money {
    private final int amount;

    public Money(int amount) {
        this.amount = amount;
    }

    public Money() {
        this(0);
    }

    public Money multiply(double rate) {
        return new Money((int) (this.amount * rate));
    }

    public Money add(int amount) {
        return new Money(this.amount + amount);
    }

    public Money add(Money money) {
        return add(money.amount);
    }

    public Money subtract(int amount) {
        return new Money(this.amount - amount);
    }

    public Money subtract(Money money) {
        return subtract(money.amount);
    }

    public int getAmount() {
        return this.amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money1 = (Money) o;
        return amount == money1.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return Integer.toString(amount);
    }
}
