package blackjack.domain.money;

import blackjack.domain.result.Result;
import java.util.Objects;

public class Money {

    private final long value;

    private Money(final long value) {
        this.value = value;
    }

    public static Money from(final long value) {
        return new Money(value);
    }

    public static Money from(final String input) {
        checkNumber(input);
        checkPositive(input);
        return new Money(Long.parseLong(input));
    }

    private static void checkNumber(final String input) {
        try {
            Long.parseLong(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 양수여야 합니다.");
        }
    }

    private static void checkPositive(final String input) {
        if (Long.parseLong(input) <= 0) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 양수여야 합니다.");
        }
    }

    Money calculate(final Result result) {
        if (result ==  Result.BLACKJACK) {
            return Money.from((long) (value * 1.5));
        }
        if (result == Result.WIN) {
            return Money.from(value);
        }
        if (result == Result.DRAW) {
            return Money.from(0);
        }
        return Money.from(-value);
    }

    public long getValue() {
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
