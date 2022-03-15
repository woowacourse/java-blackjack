package blackjack.domain.user;

import java.util.Objects;

public class UserName {

    private final String name;

    public UserName(String name) {
        validateEmptyName(name);

        this.name = name;
    }

    private void validateEmptyName(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("이름으로 공백을 입력할 수 없습니다.");
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
        UserName playerName1 = (UserName) o;
        return Objects.equals(name, playerName1.name);
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
