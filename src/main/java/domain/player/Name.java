package domain.player;

import java.util.Objects;

public class Name {
    private final String name;

    public Name(final String name) {
        validate(name);
        this.name = name;
    }

    private void validate(final String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("이름이 공백입니다.");
        }
    }

    public String getValue() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return name.equals(name1.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
