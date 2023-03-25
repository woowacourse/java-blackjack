package blackjack.domain.participants;

import java.util.Objects;

public class Money {

    private final double value;

    public Money(final double value) {
        this.value = value;
    }

    public Money add(final Money add) {
        return new Money(value + add.value);
    }

    public Money multiple(final double profit) {
        return new Money(value * profit);
    }

    public boolean isDividedBy(final Money divide) {
        return (int) (value % divide.value) == 0;
    }

    public boolean isBiggerThan(final Money other) {
        return value > other.value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Money money = (Money) o;
        return Double.compare(money.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
