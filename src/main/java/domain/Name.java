package domain;

import java.util.Objects;

public class Name {

    private final String value;

    public Name(String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름은 빈 값일 수 없습니다.");
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Name name = (Name) other;
        return Objects.equals(value, name.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
