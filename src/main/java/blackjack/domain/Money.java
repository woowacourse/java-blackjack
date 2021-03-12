package blackjack.domain;

import java.util.Objects;

public class Money {

    public static final Money ZERO = Money.of(0.0);
    private final double value;

    private Money(double value) {
        this.value = value;
    }

    public static Money of(double value) {
        return new Money(value);
    }

    public Money multiply(Money money) {
        return new Money(value * money.toDouble());
    }

    public int toInt() {
        return (int) value;
    }

    public double toDouble() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money = (Money) o;
        return Double.compare(money.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
