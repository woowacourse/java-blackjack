package blackjack.model.user;

import java.util.regex.Pattern;

public class Username {

    private static final String ERROR_EMPTY_INPUT = "입력값은 공백일 수 없습니다.";
    private static final String ERROR_INVALID_PLAYER_NAME = "플레이어의 이름은 영어 or 한글로만 이루어질 수 있습니다.";
    private static final String PLAYER_NAME_REGEX = "^[a-zA-Z가-힣]*$";

    private final String name;

    public Username(String name) {
        validateEmpty(name);
        validateRegex(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void validateEmpty(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(ERROR_EMPTY_INPUT);
        }
    }

    private void validateRegex(String name) {
        if (!Pattern.matches(PLAYER_NAME_REGEX, name)) {
            throw new IllegalArgumentException(ERROR_INVALID_PLAYER_NAME);
        }
    }
}
