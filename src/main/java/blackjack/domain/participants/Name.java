package blackjack.domain.participants;

import java.util.Objects;

public class Name {

    private final String value;

    public Name(final String value) {
        validateEmptyString(value);
        this.value = value;
    }

    private void validateEmptyString(final String value) {
        if ("".equals(value)) {
            throw new IllegalArgumentException("이름은 빈 문자열이면 안 됩니다.");
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Name name = (Name) o;
        return Objects.equals(value, name.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
