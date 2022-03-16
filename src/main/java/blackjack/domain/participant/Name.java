package blackjack.domain.participant;

import java.util.Objects;

public class Name {

    private final String name;

    private Name(String name) {
        validateEmptyName(name);
        validateNameLength(name);
        this.name = name;
    }

    public static Name of(String name) {
        return new Name(name);
    }

    private void validateEmptyName(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("공백은 허용되지 않습니다.");
        }
    }

    private void validateNameLength(String name) {
        if (name.length() > 100) {
            throw new IllegalArgumentException("길이는 100자를 초과할 수 없습니다.");
        }
    }

    public String getValue() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Name name1 = (Name)o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
