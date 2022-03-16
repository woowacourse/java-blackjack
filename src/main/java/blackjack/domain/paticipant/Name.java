package blackjack.domain.paticipant;

import java.util.Objects;

public class Name {

    private final String name;

    public Name(final String name) {
        Objects.requireNonNull(name, "이름은 null이 들어올 수 없습니다.");
        checkEmptyName(name);
        this.name = name;
    }

    private void checkEmptyName(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("이름은 공백이 들어올 수 없습니다.");
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
