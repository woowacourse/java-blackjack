package blackjack.domain;

import java.util.Objects;

public class Money {

    private final long value;

    private Money(final long value) {
        this.value = value;
        validatePositiveNumber(value);
    }

    public static Money of(long value) {
        return new Money(value);
    }

    public static Money of(final String input) {
        validateNull(input);
        validateEmpty(input);
        validateParseNumber(input);
        int value = Integer.parseInt(input.trim());
        return new Money(value);
    }

    private static void validateNull(String input) {
        Objects.requireNonNull(input, "베팅 입력값은 null이 될 수 없습니다.");
    }

    private static void validateEmpty(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("베팅 입력값은 빈값이 될 수 없습니다.");
        }
    }

    private static void validateParseNumber(String input) {
        try {
            Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("베팅 입력값은 숫자여야 합니다.");
        }
    }

    private void validatePositiveNumber(long value) {
        if (value < 1) {
            throw new IllegalArgumentException("베팅 입력값은 1이상의 양의 정수 여야합니다.");
        }
    }

    public long value() {
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
