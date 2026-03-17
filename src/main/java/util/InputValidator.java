package util;

import static constant.ErrorMessage.INVALID_INPUT_PATTERN;
import static constant.Pattern.*;

public class InputValidator {
    public static boolean validatePlayerNames(String playerNamesInput) {
        if (!playerNamesInput.matches(INPUT_PLAYER_PATTERN)) {
            throw new IllegalArgumentException(INVALID_INPUT_PATTERN.getMessage());
        }
        return true;
    }

    public static void validateBettingPrice(String bettingPrice) {
        if (!bettingPrice.matches(INPUT_BETTING_PRICE_PATTERN)) {
            throw new IllegalArgumentException(INVALID_INPUT_PATTERN.getMessage());
        }
    }

    public static void validateAdditionalCard(String isTrue) {
        if (!isTrue.matches(INPUT_ADDITIONAL_YN_PATTERN)) {
            throw new IllegalArgumentException(INVALID_INPUT_PATTERN.getMessage());
        }
    }
}
