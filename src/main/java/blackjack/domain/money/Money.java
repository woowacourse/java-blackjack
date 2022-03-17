package blackjack.domain.money;

import java.util.Objects;

public class Money {

    private final long value;

    private Money(final long value) {
        this.value = value;
    }

    public static Money valueOf(final long value) {
        return new Money(value);
    }

    public Money multiply(final double multiplier) {
        return Money.valueOf((long) (value * multiplier));
    }

    public long getValue() {
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
        return value == money.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
