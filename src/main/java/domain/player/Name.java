package domain.player;

import java.util.Objects;

public class Name {
    private static final int MIN_LENGTH = 1;
    private static final int MAX_LENGTH = 15;

    private final String name;

    public Name(final String name) {
        validateLength(name);
        this.name = name;
    }

    private static void validateLength(final String name) {
        if (name.length() < MIN_LENGTH || MAX_LENGTH < name.length()) {
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
        return name.name.equals(this.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getValue() {
        return name;
    }
}
