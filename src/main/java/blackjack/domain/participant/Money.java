package blackjack.domain.participant;

import java.util.Objects;

public class Money {
    private final int value;

    public Money(String string) throws NumberFormatException {
        this(Integer.parseInt(string));
    }

    public Money(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Money should be nonNegative");
        }
        this.value = value;
    }

    public int value() {
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
