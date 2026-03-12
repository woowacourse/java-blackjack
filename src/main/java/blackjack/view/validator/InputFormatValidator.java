package blackjack.view.validator;

import java.util.List;

public class InputFormatValidator {

    private static final String BLANK_NAME_MESSAGE = "이름은 공백일 수 없습니다.";
    private static final String INVALID_BETTING_AMOUNT_MESSAGE = "배팅 금액은 숫자여야 합니다.";
    private static final String INVALID_HIT_DECISION_MESSAGE = "y 또는 n만 입력할 수 있습니다.";

    private static final String HIT_INPUT = "y";
    private static final String STAY_INPUT = "n";

    private InputFormatValidator() {}

    public static void validateNamesFormat(final List<String> names) {
        if (names.stream().anyMatch(String::isBlank)) {
            throw new IllegalArgumentException(BLANK_NAME_MESSAGE);
        }
    }

    public static int parseToInt(final String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_BETTING_AMOUNT_MESSAGE);
        }
    }

    public static void validateHitDecision(final String input) {
        if (!input.equals(HIT_INPUT) && !input.equals(STAY_INPUT)) {
            throw new IllegalArgumentException(INVALID_HIT_DECISION_MESSAGE);
        }
    }
}
