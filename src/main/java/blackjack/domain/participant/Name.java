package blackjack.domain.participant;

import java.util.Objects;

public class Name {

    private static final String BLANK = " ";
    private final String value;

    public Name(final String value) {
        this.value = value;
        validateNull(this.value);
        validateBlank(this.value);
    }

    private void validateNull(final String name) {
        Objects.requireNonNull(name, "이름은 null 일수 없습니다.");
    }

    private void validateBlank(final String name) {
        if (name.isEmpty() || name.contains(BLANK)) {
            throw new IllegalArgumentException("이름은 공백을 포함할 수 없습니다.");
        }
    }

    public String getValue() {
        return this.value;
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
        return Objects.equals(value, name1.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
