package blackjack.domain;

public class User {

    private static final String ERROR_INVALID_NAME = "[ERROR] 유저의 이름은 한 글자 이상이어야 합니다.";

    private final String name;

    public User(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name.length() == 0) {
            throw new IllegalArgumentException(ERROR_INVALID_NAME);
        }
    }

    public String getName() {
        return name;
    }
}
