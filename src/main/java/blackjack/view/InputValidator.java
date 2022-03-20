package blackjack.view;

public class InputValidator {
    private static final String ERROR_INVALID_INTEGER = "[ERROR] 정수만 입력 가능합니다.";
    private static final String ERROR_NOT_Y_OR_N = "[ERROR] y 또는 n만 입력 가능합니다.";

    static void validateAgreement(String agreementInput) {
        if (!agreementInput.equalsIgnoreCase("y") && !agreementInput.equalsIgnoreCase("n")) {
            throw new IllegalArgumentException(ERROR_NOT_Y_OR_N);
        }
    }

    static int validateInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_INVALID_INTEGER);
        }
    }
}
