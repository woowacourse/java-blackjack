package blackjack.domain;

import java.util.Objects;

public class Money {
    private static final int MIN_VALUE = 1000;

    private final int value;

    public Money(int value) {
        validateMoney(value);
        this.value = value;
    }

    private void validateMoney(int value) {
        if (value < MIN_VALUE) {
            throw new IllegalArgumentException(String.format("배팅금액은 1,000원 이상의 양수만 가능합니다. 입력값: %d", value));
        }
    }

    public Money plus(Money other) {
        return new Money(value + other.value);
    }

    public Money multiplication(double multiplicationFactor) {
        return new Money((int) (value * multiplicationFactor));
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Money money)) {
            return false;
        }
        return value == money.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
