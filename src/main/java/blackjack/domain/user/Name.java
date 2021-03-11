package blackjack.domain.user;

import java.util.Objects;

public class Name {
    private static final String EMPTY_NAME_ERROR_MSG = "빈 값은 입력될 수 없습니다.";

    private final String name;

    private Name(String name) {
        this.name = name;
    }

    public static Name from(String name) {
        validateEmptyName(name);

        return new Name(name);
    }

    private static void validateEmptyName(String splitNames) {
        if (splitNames.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_NAME_ERROR_MSG);
        }
    }

    public String getName() {
        return name;
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
