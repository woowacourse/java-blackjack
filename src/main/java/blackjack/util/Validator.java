package blackjack.util;

import static blackjack.model.ErrorMessage.ERROR_EMPTY_INPUT;

import java.util.regex.Pattern;

public class Validator {

    public static void validateRegrex(String regrex, String input, String errorMessage) {
        if (!Pattern.matches(regrex, input)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void validateEmpty(String input, String errorMessage) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(ERROR_EMPTY_INPUT.getErrorMessage());
        }
    }
}
