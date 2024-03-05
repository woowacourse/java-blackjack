package domain;

import java.util.Objects;

public class Name {
    static final int MIN_NAME_LENGTH = 1;
    static final int MAX_NAME_LENGTH = 5;
    static final String NAME_LENGTH_MESSAGE
            = String.format("이름은 %d에서 %d글자 사이여야 합니다", MIN_NAME_LENGTH, MAX_NAME_LENGTH);

    private final String name;

    public Name(String name) {
        validateLength(name);

        this.name = name;
    }

    public String name() {
        return name;
    }

    private void validateLength(String name) {
        if (name.length() < MIN_NAME_LENGTH || MAX_NAME_LENGTH < name.length()) {
            throw new IllegalArgumentException(NAME_LENGTH_MESSAGE);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Name) obj;
        return Objects.equals(this.name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
