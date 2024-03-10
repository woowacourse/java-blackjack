package domain.gamer;

import java.util.Objects;

public class Name {
    private static final int MINIMUM_NAME_LENGTH = 1;
    private static final int MAXIMUM_NAME_LENGTH = 5;
    private static final String INVALID_NAME_LENGTH = String.format("이름은 %d글자 이상, %d글자 이하 이어야 합니다.", MINIMUM_NAME_LENGTH,
            MAXIMUM_NAME_LENGTH);
    private final String value;

    public Name(final String value) {
        validateNameLength(value);
        this.value = value;
    }

    private void validateNameLength(final String value) {
        if (value.isBlank() || value.length() > MAXIMUM_NAME_LENGTH) {
            throw new IllegalArgumentException(INVALID_NAME_LENGTH);
        }
    }

    public String getValue() {
        return value;
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
