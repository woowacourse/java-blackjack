package blackjack.domain.participant;

import java.util.Objects;

public class Name {
    private static final String DEALER_NAME = "딜러";

    private final String value;

    public Name(final String value) {
        validateNull(value);
        this.value = value.trim();
        validateEmpty(this.value);
        validateName(this.value);
    }

    private void validateNull(final String name) {
        Objects.requireNonNull(name, "이름은 null 일 수 없습니다.");
    }

    private void validateEmpty(final String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("이름은 빈값 일 수 없습니다.");
        }
    }

    private void validateName(final String name) {
        if (DEALER_NAME.equals(name)) {
            throw new IllegalArgumentException("이름으로 \"딜러\" 는 사용할 수 없습니다.");
        }
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return Objects.equals(value, name.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
