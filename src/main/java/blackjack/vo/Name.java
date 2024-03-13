package blackjack.vo;

import java.util.Objects;

public class Name {
    private final String name;

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    public String value() {
        return name;
    }

    private void validate(String value) {
        if (value.isBlank()) {
            throw new IllegalArgumentException("이름은 공백일 수 없습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
