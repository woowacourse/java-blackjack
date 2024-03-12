package domain.participant;

import constants.ErrorCode;
import exception.InvalidPlayerNameException;

public class Name {

    private final String value;

    public Name(final String value) {
        validate(value);
        this.value = value;
    }

    private void validate(final String name) {
        validateNull(name);
        validateBlank(name);
    }

    private void validateNull(final String name) {
        if (name == null) {
            throw new InvalidPlayerNameException(ErrorCode.BLANK_VALUE);
        }
    }

    private void validateBlank(final String name) {
        if (name.isBlank()) {
            throw new InvalidPlayerNameException(ErrorCode.BLANK_VALUE);
        }
    }

    public String getValue() {
        return value;
    }
}
