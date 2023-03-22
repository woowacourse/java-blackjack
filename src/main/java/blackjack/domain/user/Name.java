package blackjack.domain.user;

import java.util.Objects;

public abstract class Name {

    static final String BLANK_NAME_EXCEPTION_MESSAGE = "이름은 공백일 수 없습니다.";

    private final String value;

    protected Name(final String name) {
        this.value = name;
    }

    public final String getValue() {
        return value;
    }

    public final boolean isSame(final Name name) {
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
