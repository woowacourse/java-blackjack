package blackjack.domain.participant.human.name;

import java.util.Objects;
import java.util.regex.Pattern;

public final class Name {
    private static final String NAME_ERROR_MESSAGE = "이름 형식에 맞게 입력해야 합니다.";
    private static final Pattern NAME_PATTERN = Pattern.compile("[가-힣a-zA-Z]+");

    private final String value;

    public Name(final String value) {
        validateName(value);
        this.value = value;
    }

    private void validateName(final String input) {
        if (!NAME_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException(NAME_ERROR_MESSAGE);
        }
    }

    public String get() {
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

    @Override
    public String toString() {
        return "Name{" +
                "value='" + value + '\'' +
                '}';
    }
}
