package domain.participant;

import utils.ExceptionMessages;

public class Name {

    private final String value;

    public Name(String value) {
        validateEmpty(value);
        this.value = value;
    }

    private void validateEmpty(String value) {
        if (value.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.EMPTY_NAME_ERROR);
        }
    }

    public String getValue() {
        return value;
    }
}
