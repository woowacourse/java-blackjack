package domain.name;

import java.util.Objects;

public class Name {
    private static final int NAME_MAX_LENGTH = 5;
    private static final String ILLEGAL_NAME = "딜러";
    private final String name;

    public Name(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(final String name) {
        validateBlank(name);
        validateLength(name);
        validateNotProperName(name);
    }

    private void validateNotProperName(final String name) {
        if (name.equals(ILLEGAL_NAME)) {
            throw new IllegalArgumentException("딜러라는 이름은 사용할 수 없습니다.");
        }
    }

    private void validateBlank(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("플레이어의 이름은 공백으로만 이루어질 수 없습니다.");
        }
    }

    private void validateLength(final String name) {
        if (name.length() > NAME_MAX_LENGTH) {
            throw new IllegalArgumentException(String.format("플레이어의 이름은 %d보다 길 수 없습니다.", NAME_MAX_LENGTH));
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Name other = (Name) obj;
        return name == other.name;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
