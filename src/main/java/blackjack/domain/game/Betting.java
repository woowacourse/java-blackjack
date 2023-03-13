package blackjack.domain.game;

import java.util.regex.Pattern;

public class Betting {

    private static final Pattern BETTING_FORMAT = Pattern.compile("[0-9]+");
    private static final int MAX_BETTING_RANGE = 1_000_000;
    private static final int MIN_BETTING_RANGE = 1_000;
    private static final String INVALID_FORMAT_ERROR_MESSAGE = "베팅 금액은 숫자만 가능합니다.";
    private static final String INVALID_LENGTH_ERROR_MESSAGE = "베팅 금액은 1,000원 이상, 1,000,000원 이하만 가능합니다.";

    private final int value;

    private Betting(final String value) {
        validate(value);
        this.value = Integer.parseInt(value);
    }

    private void validate(final String value) {
        validateFormat(value);
        validateRange(value);
    }

    private void validateFormat(final String value) {
        if (isNotNumber(value)) {
            throw new IllegalArgumentException(INVALID_FORMAT_ERROR_MESSAGE);
        }
    }

    private boolean isNotNumber(final String value) {
        return !BETTING_FORMAT.matcher(value).matches();
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
        return new Betting(value);
    }

    public static Betting from(final int value) {
        return new Betting(String.valueOf(value));
    }

    public int getValue() {
        return value;
    }
}
