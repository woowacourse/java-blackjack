package blackjack.model.user;

import static blackjack.model.constant.ErrorMessage.ERROR_EMPTY_INPUT;
import static blackjack.model.constant.ErrorMessage.ERROR_INVALID_PLAYER_NAME;

import java.util.regex.Pattern;

public class UserName {

    private static final String PLAYER_NAME_REGREX = "^[a-zA-Z가-힣]*$";

    private final String name;

    public UserName(String name) {
        validateEmpty(name);
        validateRegrex(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void validateEmpty(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException(ERROR_EMPTY_INPUT.getErrorMessage());
        }
    }

    public static void validateRegrex(String name) {
        if (!Pattern.matches(PLAYER_NAME_REGREX, name)) {
            throw new IllegalArgumentException(ERROR_INVALID_PLAYER_NAME.getErrorMessage());
        }
    }
}
