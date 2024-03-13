package blackjack.domain.participant;

import java.util.Objects;

public class Name {
    private static final int MINIMUM_LENGTH_OF_NAME = 2;
    private static final int MAXIMUM_LENGTH_OF_NAME = 5;

    private final String name;

    public Name(final String name) {
        validateLength(name);
        this.name = name;
    }

    private void validateLength(final String name) {
        final int length = name.length();

        if (length < MINIMUM_LENGTH_OF_NAME || length > MAXIMUM_LENGTH_OF_NAME) {
            throw new IllegalArgumentException(
                    String.format("이름의 길이가 %d보다 작거나 %d보다 클 수 없습니다.", MINIMUM_LENGTH_OF_NAME, MAXIMUM_LENGTH_OF_NAME));
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Name other = (Name) o;
        return Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
