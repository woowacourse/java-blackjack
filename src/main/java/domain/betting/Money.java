package domain.betting;

import java.util.Objects;

public class Money {
    private static final int MINIMUM_BETTING_MONEY = 0;
    static final String INVALID_BETTING_MESSAGE = "돈은 음수가 될 수 없습니다.";

    private final int value;

    public Money(final int value) {
        validateRange(value);
        this.value = value;
    }

    private Money(final double value) {
        this.value = (int) value;
    }

    public void validateRange(final int value) {
        if (value < MINIMUM_BETTING_MONEY) {
            throw new IllegalArgumentException(INVALID_BETTING_MESSAGE);
        }
    }

    public Money multiply(double rate) {
        return new Money(this.value * rate);
    }

    public int getValue() {
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
        Money money = (Money) o;
        return this.value == money.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
