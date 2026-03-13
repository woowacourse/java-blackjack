package model.participant;

import constant.ErrorMessage;

public class Agreement {
    private static final String TRUE_TEXT = "y";
    private static final String FALSE_TEXT = "n";

    private final boolean value;

    public Agreement(String value) {
        validate(value);
        this.value = value.equals(TRUE_TEXT);
    }

    public boolean get() {
        return value;
    }

    private void validate(String value) {
        validateEmptyValue(value);
        validatePossibleValue(value);
    }

    private void validateEmptyValue(String value) {
        if(value.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_IS_BLANK.getMessage());
        }
    }

    private void validatePossibleValue(String value) {
        if(!value.equals(TRUE_TEXT) && !value.equals(FALSE_TEXT)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_CONDITION_INPUT.getMessage());
        }
    }
}
