package blackjack.domain.participant;

import java.util.Objects;

public class Name {

    private static final int LENGTH_OF_MINIMUM_PLAYER_NAME = 1;
    private static final int LENGTH_OF_MAXIMUM_PLAYER_NAME = 5;

    private final String value;

    public Name(final String value) {
        validateName(value);
        this.value = value;
    }

    private void validateName(String value) {
        validateContainBlank(value);
        validateLengthOfName(value);
    }

    private void validateContainBlank(final String value) {
        if (value.isBlank()) {
            throw new IllegalArgumentException("이름에는 공백이 포함될 수 없습니다.");
        }
    }

    private void validateLengthOfName(final String value) {
        if (value.length() < LENGTH_OF_MINIMUM_PLAYER_NAME || value.length() > LENGTH_OF_MAXIMUM_PLAYER_NAME) {
            throw new IllegalArgumentException("이름의 길이는 1이상 5이하만 가능합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return value.equals(name.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public String getValue() {
        return this.value;
    }
}
