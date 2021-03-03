package blackjack.domain.participant;

import java.util.Objects;

public class Name {

    private static final String BLANK = " ";
    private final String name;

    public Name(final String name) {
        this.name = name;
        validateNull(this.name);
        validateBlank(this.name);
    }

    private void validateNull(String name) {
        Objects.requireNonNull(name, "이름은 null 일수 없습니다.");
    }

    private void validateBlank(String name) {
        if (name.isEmpty() || name.contains(BLANK)) {
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
