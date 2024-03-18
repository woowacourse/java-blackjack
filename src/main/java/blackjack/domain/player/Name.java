package blackjack.domain.player;

import java.util.Objects;

public record Name(String value) {
    private static final int MINIMUM_LENGTH = 1;
    private static final int MAXIMUM_LENGTH = 10;

    public Name {
        validateLength(value);
    }

    private void validateLength(final String value) {
        if (MINIMUM_LENGTH <= value.length() && value.length() <= MAXIMUM_LENGTH) {
            return;
        }

        final String errorMessage = String.format("[ERROR] 이름의 길이는 %s 이상, %s 이하여야 합니다.",
                MINIMUM_LENGTH, MAXIMUM_LENGTH);
        throw new IllegalArgumentException(errorMessage);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Name name = (Name) o;

        return Objects.equals(value, name.value);
    }
}
