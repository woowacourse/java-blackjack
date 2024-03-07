package domain;

import java.util.Objects;

public class Name {
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 5;

    private final String name;

    public Name(String name) {
        validateLength(name);
        this.name = name;
    }

    private void validateLength(String name) {
        int length = name.length();

        if (length < MIN_NAME_LENGTH || MAX_NAME_LENGTH < length) {
            throw new IllegalArgumentException(
                    String.format("rejected value : %s - 이름은 %d ~ %d 글자여야 합니다", name, MIN_NAME_LENGTH, MAX_NAME_LENGTH)
            );
        }
    }

    public String getValue() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Name other) {
            return this.name.equals(other.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
