package blackjack.util;

import static blackjack.model.ErrorMessage.ERROR_EMPTY_INPUT;
import static blackjack.model.ErrorMessage.ERROR_INVALID_PLAYER_NAME;

import blackjack.model.Player;
import java.util.Arrays;
import java.util.List;

public class PlayerParser {

    private static final String PLAYER_NAME_REGREX = "^[a-zA-Z가-힣]*$";

    public static List<Player> parse(String names) {
        return Arrays.stream(names.split(","))
                .map(String::trim)
                .peek((name) -> Validator.validateRegrex(PLAYER_NAME_REGREX, name,
                        ERROR_INVALID_PLAYER_NAME.getErrorMessage()))
                .peek((name) -> Validator.validateEmpty(name, ERROR_EMPTY_INPUT.getErrorMessage()))
                .map(Player::new)
                .toList();
    }

}
