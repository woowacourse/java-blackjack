package model;

import java.util.Objects;

public class Money {

    private final int value;

    public Money(int value) {
        validateRange(value);
        this.value = value;
    }

    public static Money from(String input) {
        return new Money(parseInteger(input));
    }

    private static int parseInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자 형식이어야 합니다");
        }
    }

    private void validateRange(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("0원 이상의 금액을 입력해야 합니다.");
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return value == money.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
