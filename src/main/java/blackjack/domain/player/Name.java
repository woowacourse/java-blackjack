package blackjack.domain.player;

import java.util.Objects;

public class Name {

    private final String name;

    public Name(final String name) {
        validateNameLength(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void validateNameLength(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("최소 이름의 길이는 1자 이상입니다.");
        }
    }

    @Override
    public boolean equals(final Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Name name1 = (Name) object;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
