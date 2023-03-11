package blackjack.domain;

import java.util.Objects;

public class Money {

    private final int value;

    public Money(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("범위가 올바르지 않습니다.");
        }
        this.value = value;
    }

    public int getValue() {
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

    public Money product(double factor) {
        return new Money((int) (value * factor));
    }
}
