package blackjack.model.betting;

import java.util.Objects;

public class Money {
    private final int value;

    public Money(final int value) {
        this.value = value;
    }

    public Money multiple(final double rate) {
        return new Money((int) (value * rate));
    }

    @Override
    public boolean equals(final Object o) {
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
