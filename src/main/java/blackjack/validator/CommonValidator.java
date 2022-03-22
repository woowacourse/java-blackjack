package blackjack.validator;

public class CommonValidator {

    static final String NOT_ALLOW_BLANK_MESSAGE = "[ERROR] 공백을 허용하지 않습니다.";
    static final String NOT_ALLOW_NUMBER_MESSAGE = "[ERROR] 숫자가 아닙니다.";

    private CommonValidator() {
    }

    public static void isBlank(String value) {
        if (value.isBlank()) {
            throw new IllegalArgumentException(NOT_ALLOW_BLANK_MESSAGE);
        }
    }

    public static void isNumber(String value) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(NOT_ALLOW_NUMBER_MESSAGE);
        }
    }
}
