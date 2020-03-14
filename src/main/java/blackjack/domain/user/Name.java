package blackjack.domain.user;

import java.util.Objects;

public class Name {
    private static final String NULL_ERR_MSG = "%s이(가) 없습니다.";
    private static final String MAX_PLAYER_NAME_ERR_MSG = "플레이어 이름은 최대 %자입니다.";
    private static final int MAX_NAME_LENGTH = 5;
    private String name;

    public Name(String name) {
        validateEmpty(name);
        validateLength(name);
        this.name = name;
    }

    private void validateEmpty(String name) {
        if (name.isEmpty()) {
            Objects.requireNonNull(name, String.format(NULL_ERR_MSG, "사용자 이름"));
        }
    }

    private void validateLength(String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(String.format(MAX_PLAYER_NAME_ERR_MSG, MAX_NAME_LENGTH));
        }
    }

    public String getName() {
        return name;
    }
}
