package domain.gamer;

import java.util.Objects;

public class Money {
    private final double value;

    public Money(double value) {
        this.value = value;
    }

    public Money multiply(double operand) {
        return new Money(this.value * operand);
    }

    public Money plus(Money operand) {
        return new Money(this.value + operand.value);
    }

    public Money negate() {
        return new Money(-this.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Double.compare(money.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public double getValue() {
        return value;
    }
}
