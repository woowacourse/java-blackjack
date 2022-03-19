package blackjack.domain.vo;

import java.util.Objects;

public class Name {

    private final String name;

    private Name(String name) {
        this.name = name;
    }

    public static Name of(String name) {
        validateName(name);

        return new Name(name);
    }

    private static void validateName(String name) {
        validateEmpty(name);
        validateSize(name);
    }

    private static void validateSize(String name) {
        if (name.length() == 0) {
            throw new IllegalArgumentException("이름이 공백일 수 없습니다.");
        }
    }

    private static void validateEmpty(String name) {
        if (name.contains(" ")) {
            throw new IllegalArgumentException("이름에 공백을 포함할 수 없습니다.");
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
        Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
