package blackjack.domain.money;

import java.util.Objects;

public class Money {

    private final int value;

    public Money(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Money multiply(final Double rate) {
        return new Money((int) (rate * value));
    }

    public Money opposite() {
        return new Money(-1 * value);
    }

    public Money sum(final Money money) {
        return new Money(this.value + money.value);
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
        return value == money.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
