package blackjack.domain.user;

import java.util.Objects;

public class Name {

    static final String BLANK_NAME_EXCEPTION_MESSAGE = "이름은 공백일 수 없습니다.";

    private final String value;

    public Name(final String name) {
        validateBlank(name);
        this.value = name;
    }

    private void validateBlank(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(BLANK_NAME_EXCEPTION_MESSAGE);
        }
    }

    public String getValue() {
        return value;
    }

    public boolean isSame(final Name name) {
        return this.value.equals(name.value);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Name name = (Name) o;
        return Objects.equals(value, name.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
