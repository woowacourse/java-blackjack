package blackjack.domain;

import java.util.Objects;

public class Money {

    private static final int MIN_MONEY = 1;

    private final int value;

    public Money(int value) {
        validateMoneyRange(value);

        this.value = value;
    }

    private void validateMoneyRange(int money) {
        if (money < MIN_MONEY) {
            throw new IllegalArgumentException("돈은 0보다 커야 합니다.");
        }
    }

    public Money multiply(double multiplier) {
        return new Money((int) (value * multiplier));
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
}
