package domain.money;

import java.util.Objects;

public class Money {
    private final int value;

    public Money(final int value) {
        this.value = value;
    }

    public Money add(final Money money) {
        return new Money(value + money.value);
    }

    public Money subtract(final Money money) {
        return new Money(value - money.value);
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
