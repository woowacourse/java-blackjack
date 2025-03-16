package domain.participant;

import java.util.Objects;

public class Money {

    public static final Money ZERO = new Money(0);
    private static final int AMOUNT_VALUE = 100000;

    private final int value;

    private Money(int value) {
        this.value = value;
    }

    public static Money of(int value) {
        return new Money(value);
    }

    public static Money of(String rawValue) {
        validateNullOrBlank(rawValue);
        validateIntegerFormat(rawValue);
        validatePositiveNumber(rawValue);
        validateAmountUnit(rawValue);
        return new Money(Integer.parseInt(rawValue));
    }

    private static void validateNullOrBlank(String rawValue) {
        if (rawValue == null || rawValue.isBlank()) {
            throw new IllegalArgumentException("입력이 공백이거나 null 입니다.");
        }
    }

    private static void validateIntegerFormat(String rawValue) {
        try {
            Integer.parseInt(rawValue);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력이 정수 형식이 아닙니다.");
        }
    }

    private static void validatePositiveNumber(String rawValue) {
        if (Integer.parseInt(rawValue) <= 0) {
            throw new IllegalArgumentException("입력은 0보다 커야 합니다.");
        }
    }

    private static void validateAmountUnit(String rawValue) {
        if (Integer.parseInt(rawValue) % AMOUNT_VALUE != 0) {
            throw new IllegalArgumentException("입력은 10만원 단위입니다.");
        }
    }

    public Money times(double count) {
        return new Money((int) (value * count));
    }

    public Money plus(Money money) {
        return new Money(value + money.value);
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
        return Objects.hashCode(value);
    }
}
