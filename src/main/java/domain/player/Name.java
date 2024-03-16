package domain.player;

import java.util.Objects;

public class Name {
    private static final int MAX_LENGTH = 5;
    private final String value;

    public Name(final String name) {
        validate(name);
        value = name;
    }

    private void validate(final String name) {
        validateEmptiness(name);
        validateLength(name);
    }

    private void validateLength(final String name) {
        if (name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(String.format("이름 길이는 %d자 이하로 입력해주세요", MAX_LENGTH));
        }
    }

    private void validateEmptiness(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름은 비어있을 수 없습니다.");
        }
    }

    public String getValue() {
        return value;
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
