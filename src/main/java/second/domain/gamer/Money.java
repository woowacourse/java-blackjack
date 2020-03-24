package second.domain.gamer;

import java.util.Objects;

public class Money {
    private final int value;

    public Money() {
        this.value = 0;
    }

    public Money(final int value) {
        this.value = value;
    }

    public Money times(double value) {
        return new Money((int) (this.value * value));
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return value == money.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
