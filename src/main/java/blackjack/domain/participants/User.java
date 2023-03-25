package blackjack.domain.participants;

import java.util.Objects;

public class User {

    private static final int MAX_NAME_LENGTH = 100;

    private final String username;

    User(String username) {
        validateName(username);
        this.username = username;
    }

    private void validateName(String name) {
        validateEmptyName(name);
        validateLength(name);
    }

    private void validateEmptyName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("이름을 입력하지 않았습니다");
    }

    private void validateLength(String name) {
        if (name.length() > MAX_NAME_LENGTH)
            throw new IllegalArgumentException("이름이 " + MAX_NAME_LENGTH + "글자를 초과했습니다");
    }

    String getName() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
