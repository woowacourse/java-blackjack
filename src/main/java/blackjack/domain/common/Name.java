package blackjack.domain.common;

import java.util.Objects;

public class Name {
    private final String value;

    public Name(final String value) {
        validateContainBlank(value);
        this.value = value;
    }

    public String asString() {
        return value;
    }

    private void validateContainBlank(final String value) {
        if (value.contains(" ")) {
            throw new IllegalArgumentException("플레이어 이름에는 공백이 포함될 수 없습니다.");
        }
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (!(object instanceof final Name name)) return false;
        return Objects.equals(this.value, name.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }
}
