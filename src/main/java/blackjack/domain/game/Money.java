package blackjack.domain.game;

import java.util.Objects;

public class Money {

    private final double value;

    public Money(double value) {
        this.value = value;
    }

    public Money multiply(double yield) {
        return new Money(this.value * yield);
    }

    public Money subtract(Money money) {
        return new Money(this.value - money.value);
    }

    public double getValue() {
        return value;
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

    @Override
    public String toString() {
        return "Money{" +
                "value=" + value +
                '}';
    }
}
