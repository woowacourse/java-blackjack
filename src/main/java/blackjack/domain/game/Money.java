package blackjack.domain.game;

import java.util.Objects;

public class Money {

    private final double value;

    public Money(int value) {
        this.value = value;
    }

    public Money multiply(double yield) {
        return new Money((int) (this.value * yield));
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
}
