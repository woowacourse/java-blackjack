package blackjack.model.user;

import static blackjack.model.constant.ErrorMessage.ERROR_EMPTY_INPUT;
import static blackjack.model.constant.ErrorMessage.ERROR_INVALID_PLAYER_NAME;

import java.util.regex.Pattern;

public class Username {

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
            throw new IllegalArgumentException(ERROR_EMPTY_INPUT.getErrorMessage());
        }
    }

    public static void validateRegex(String name) {
        if (!Pattern.matches(PLAYER_NAME_REGEX, name)) {
            throw new IllegalArgumentException(ERROR_INVALID_PLAYER_NAME.getErrorMessage());
        }
    }
}
