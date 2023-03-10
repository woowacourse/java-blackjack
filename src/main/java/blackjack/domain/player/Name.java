package blackjack.domain.player;

import java.util.Objects;

public class Name {
    private static final int MAXIMUM_NAMES_RANGE = 10;
    private final String name;

    public Name(String name) {
        validateNullOrEmpty(name);
        validateHasBlank(name);
        validateRange(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void validateNullOrEmpty(String name) {
        if (Objects.isNull(name) || name.length() == 0) {
            throw new IllegalArgumentException("이름은 빈값을 입력할 수 없습니다.");
        }
    }

    public void validateHasBlank(String name) {
        if (name.contains(" ")) {
            throw new IllegalArgumentException("이름은 공백을 포함할 수 없습니다.");
        }
    }

    private void validateRange(String name) {
        if (name.length() > MAXIMUM_NAMES_RANGE) {
            throw new IllegalArgumentException("각 이름의 수가 1이상 10이하여야 합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
