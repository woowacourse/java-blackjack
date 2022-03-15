package blackjack.domain;

import java.util.Objects;

public class Money {

    private final long value;

    private Money(long value) {
        this.value = value;
    }

    public static Money from(long value) {
        return new Money(value);
    }

    public static Money from(String input) {
        checkNumber(input);
        checkPositive(input);
        return new Money(Long.parseLong(input));
    }

    private static void checkNumber(String input) {
        try {
            Long.parseLong(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 양수여야 합니다.");
        }
    }

    private static void checkPositive(String input) {
        if (Long.parseLong(input) <= 0) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 양수여야 합니다.");
        }
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
