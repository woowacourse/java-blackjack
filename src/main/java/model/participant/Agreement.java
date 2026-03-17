package model.participant;

import constant.ErrorMessage;

public record Agreement(boolean value) {
    private static final String TRUE_TEXT = "y";
    private static final String FALSE_TEXT = "n";

    public Agreement(String value) {
        this(validate(value));
    }

    private static boolean validate(String value) {
        validateEmptyValue(value);
        validatePossibleValue(value);

        return value.equals(TRUE_TEXT);
    }

    private static void validateEmptyValue(String value) {
        if (value.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_IS_BLANK.getMessage());
        }
    }

    private static void validatePossibleValue(String value) {
        if (!value.equals(TRUE_TEXT) && !value.equals(FALSE_TEXT)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_CONDITION_INPUT.getMessage());
        }
    }
}
