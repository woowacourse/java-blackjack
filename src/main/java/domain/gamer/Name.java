package domain.gamer;

import exception.NameBlankException;
import exception.NameLengthException;
import java.util.Objects;

public class Name {
    public static final int MINIMUM_NAME_LENGTH = 1;
    public static final int MAXIMUM_NAME_LENGTH = 5;
    private final String value;

    public Name(final String value) {
        validateNameLength(value);
        validateIsBlank(value);
        this.value = value;
    }

    private void validateNameLength(final String value) {
        int length = value.length();
        if (length < MINIMUM_NAME_LENGTH || length > MAXIMUM_NAME_LENGTH) {
            throw new NameLengthException(NameLengthException.INVALID_NAME_LENGTH);
        }
    }

    private void validateIsBlank(final String value) {
        if (value.isBlank()) {
            throw new NameBlankException(NameBlankException.NOT_ALLOWED_BLANK_NAME);
        }
    }

    public String getValue() {
        return value;
    }

    public boolean isSame(final String name) {
        return value.equals(name);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Name name = (Name) o;
        return Objects.equals(value, name.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
