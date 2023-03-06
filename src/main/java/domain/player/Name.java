package domain.player;

import java.util.Objects;

public class Name {
    private static final int MIN_LENGTH = 1;
    private static final int MAX_LENGTH = 15;

    private final String value;

    public Name(final String value) {
        validateLength(value);
        this.value = value;
    }

    private static void validateLength(final String value) {
        if (value.length() < MIN_LENGTH || MAX_LENGTH < value.length()) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Name name = (Name) obj;
        return name.value.equals(this.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public String getValue() {
        return value;
    }
}
