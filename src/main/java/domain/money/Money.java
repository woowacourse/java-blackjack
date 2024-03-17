package domain.money;

import java.util.Objects;

public class Money {
    private static final int MIN_MONEY = 1;
    private final int value;

    public Money(final int value) {
        validate(value);
        this.value = value;
    }

    private void validate(final int value) {
        if (value < MIN_MONEY) {
            throw new IllegalArgumentException(MIN_MONEY + "이상의 숫자를 입력해주세요.");
        }
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

    public int getValue() {
        return value;
    }
}
