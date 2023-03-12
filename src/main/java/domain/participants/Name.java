package domain.participants;

import domain.message.ExceptionMessage;
import java.util.Objects;

public class Name {

    private static final int MAX_NAME_LENGTH = 10;

    private final String name;

    public Name(final String name) {
        validate(name);
        this.name = name;
    }

    private void validate(final String name) {
        validateIsNullOrBlank(name);
        validateNameLength(name);
    }

    private void validateIsNullOrBlank(final String name) {
        if (Objects.isNull(name) || name.isBlank()) {
            throw new IllegalArgumentException(ExceptionMessage.NAME_IS_EMPTY.getMessage());
        }
    }

    private void validateNameLength(final String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(ExceptionMessage.NAME_LENGTH_OVER.getMessage());
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Name)) {
            return false;
        }
        Name name1 = (Name) o;
        return name.equals(name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
