package domain.participant;

import constants.ErrorCode;
import exception.InvalidPlayerNameException;
import java.util.Objects;

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

    @Override
    public boolean equals(final Object target) {
        if (this == target) {
            return true;
        }
        if (!(target instanceof Name name)) {
            return false;
        }
        return Objects.equals(value, name.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
