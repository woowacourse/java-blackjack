package blackjack.domain.player;

import java.util.Objects;

public class Name {

    private static final String BLANK_NAME_ERROR_MESSAGE = "이름으로 공백을 입력할 수 없습니다.";
    private final String name;

    public Name(String name) {
        validateEmptyName(name);

        this.name = name;
    }

    private void validateEmptyName(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(BLANK_NAME_ERROR_MESSAGE);
        }
    }

    public String get() {
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
        Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Name{" +
                "name='" + name + '\'' +
                '}';
    }
}
