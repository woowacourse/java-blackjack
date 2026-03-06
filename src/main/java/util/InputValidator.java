package util;

import static constant.ErrorMessage.INVALID_INPUT_PATTERN;
import static constant.Pattern.INPUT_ADDITIONAL_YN_PATTERN;
import static constant.Pattern.INPUT_PLAYER_PATTERN;

public class InputValidator {
    public static boolean validatePlayerNames(String playerNamesInput) {
        if (!playerNamesInput.matches(INPUT_PLAYER_PATTERN)) {
            throw new IllegalArgumentException(INVALID_INPUT_PATTERN.getMessage());
        }
        return true;
    }

    public static void validateAdditionalCard(String isTrue) {
        if (!isTrue.matches(INPUT_ADDITIONAL_YN_PATTERN)) {
            throw new IllegalArgumentException(INVALID_INPUT_PATTERN.getMessage());
        }
    }
}
