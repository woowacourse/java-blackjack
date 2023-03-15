package blackjack.domain.game;

import java.util.Objects;

public class Betting {

    private static final int MAX_BETTING_RANGE = 1_000_000;
    private static final int MIN_BETTING_RANGE = 1_000;
    private static final String INVALID_FORMAT_ERROR_MESSAGE = "베팅 금액은 숫자만 가능합니다.";
    private static final String INVALID_LENGTH_ERROR_MESSAGE = "베팅 금액은 1,000원 이상, 1,000,000원 이하만 가능합니다.";

    private final int value;

    private Betting(final String value, final boolean rangeValidateLimit) {
        validate(value, rangeValidateLimit);
        this.value = Integer.parseInt(value);
    }

    private void validate(final String value, final boolean rangeValidateLimit) {
        validateNumber(value);
        if (rangeValidateLimit) {
            validateRange(value);
        }
    }

    private void validateNumber(final String value) {
        if (isNotNumber(value)) {
            throw new IllegalArgumentException(INVALID_FORMAT_ERROR_MESSAGE);
        }
    }

    private boolean isNotNumber(final String value) {
        try {
            Integer.parseInt(value);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    private void validateRange(final String value) {
        final int numValue = Integer.parseInt(value);

        if (isLessMoney(numValue) || isExceedMoney(numValue)) {
            throw new IllegalArgumentException(INVALID_LENGTH_ERROR_MESSAGE);
        }
    }

    private static boolean isLessMoney(int numValue) {
        return MIN_BETTING_RANGE > numValue;
    }

    private static boolean isExceedMoney(int numValue) {
        return MAX_BETTING_RANGE < numValue;
    }

    public static Betting from(final String value) {
        return new Betting(value, true);
    }

    public static Betting from(final int value) {
        return new Betting(String.valueOf(value), false);
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Betting betting = (Betting) o;
        return value == betting.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
