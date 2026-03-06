package model;

import constant.ErrorMessage;

public class Agreement {
    private final boolean value;

    public Agreement(String value) {
        validate(value);
        this.value = value.equals("y");
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
        if(!value.equals("y") && !value.equals("n")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_CONDITION_INPUT.getMessage());
        }
    }
}
