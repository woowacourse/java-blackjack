package domain.participant;

import constants.ErrorCode;
import exception.InvalidPlayerNameException;
import exception.ReservedPlayerNameException;
import java.util.Objects;

public class Name {

    public static final Name RESERVED_NAME = new Name();

    private final String value;

    private Name() {
        this.value = "딜러";
    }

    public Name(final String value) {
        validate(value.trim());
        this.value = value.trim();
    }

    private void validate(final String name) {
        validateNull(name);
        validateBlank(name);
        validateReserved(name);
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

    private void validateReserved(final String name) {
        if (RESERVED_NAME.value.equals(name)) {
            throw new ReservedPlayerNameException(ErrorCode.RESERVED_NAME);
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
