package blackjack.domain;

import java.util.Objects;

public class Name {
    private final String value;

    public Name(final String input) {
        validateBlank(input);
        this.value = input;
    }

    private void validateBlank(final String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("참여자 이름에 공백을 입력할 수 없습니다.");
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
        final Name name1 = (Name) o;
        return Objects.equals(value, name1.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
