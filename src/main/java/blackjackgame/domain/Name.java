package blackjackgame.domain;

import java.util.Objects;

public class Name {
    private static final int MAX_NAME_LENGTH = 15;

    private final String name;

    public Name(final String name) {
        validateBlank(name);
        String trimmedName = name.trim();
        validateRange(trimmedName);
        this.name = trimmedName;
    }

    private void validateBlank(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("플레이어의 이름은 공백일 수 없습니다.");
        }
    }

    private void validateRange(final String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("플레이어의 이름은 " + MAX_NAME_LENGTH + "글자를 초과할 수 없습니다.");
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Name compareName = (Name) o;
        return Objects.equals(name, compareName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
