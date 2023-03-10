package blackjack.domain.game;

import java.util.Objects;

public class Money {

    private final int value;

    public Money(final String value) {
        final int parsedValue = validateInteger(value);
        validateRangeOfMoney(parsedValue);
        this.value = parsedValue;
    }

    private int validateInteger(final String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("베팅 금액은 숫자만 가능합니다.");
        }
    }

    private void validateRangeOfMoney(final int value) {
        if (value < 1000 || value > 1000000) {
            throw new IllegalArgumentException("베팅 금액은 1000~1000000사이의 숫자만 가능합니다.");
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Money money = (Money) o;
        return value == money.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
