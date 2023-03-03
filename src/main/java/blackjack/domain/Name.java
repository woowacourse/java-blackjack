package blackjack.domain;

import java.util.Objects;

public class Name {
    private static final int MIN = 1;
    private static final int MAX = 5;
    private final String value;

    public Name(final String value) {
        final String trimmedValue = value.trim();
        validateLength(trimmedValue);
        this.value = trimmedValue;
    }

    private void validateLength(final String value) {
        if (value.length() > MAX || value.length() < MIN) {
            throw new IllegalArgumentException(
                    String.format("이름은 %d~%d 글자만 허용합니다.", MIN, MAX)
            );
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Name name = (Name) o;
        return Objects.equals(value, name.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
