package domain.player;

import java.util.Objects;

public class Money {

    private final double amount;

    private Money(final double amount) {
        this.amount = amount;
    }

    public static Money of(final double amount) {
        return new Money(amount);
    }

    public double amount() {
        return amount;
    }

    public Money plus(final Money money) {
        return new Money(amount + money.amount);
    }

    public Money minus(final Money money) {
        return new Money(amount - money.amount);
    }

    public Money times(final double times) {
        return new Money(amount * times);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Money)) return false;
        final Money money = (Money) o;
        return Double.compare(money.amount, amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
