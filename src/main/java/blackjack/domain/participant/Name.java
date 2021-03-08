package blackjack.domain.participant;

import java.util.Objects;

public class Name {

    private final String value;

    public Name(final String value) {
        this.value = value.trim();
        validateNull(this.value);
        validateEmpty(this.value);
    }

    private void validateNull(String name) {
        Objects.requireNonNull(name, "이름은 null 일 수 없습니다.");
    }

    private void validateEmpty(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("이름은 빈값 일 수 없습니다.");
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
