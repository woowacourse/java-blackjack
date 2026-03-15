package util;

import static constant.ErrorMessage.INVALID_BET_AMOUNT_UNIT;
import static constant.ErrorMessage.INVALID_INPUT_PATTERN;
import static constant.Pattern.INPUT_ADDITIONAL_YN_PATTERN;
import static constant.Pattern.INPUT_NUMBER_PATTERN;
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

    public static void validateBetAmount(String betAmount) {
        if (betAmount == null || betAmount.isBlank()) {
            throw new IllegalArgumentException(INVALID_INPUT_PATTERN.getMessage());
        }
        if (!betAmount.matches(INPUT_NUMBER_PATTERN)) {
            throw new IllegalArgumentException(INVALID_INPUT_PATTERN.getMessage());
        }
        if (Integer.parseInt(betAmount) % 10 != 0) {
            throw new IllegalArgumentException(INVALID_BET_AMOUNT_UNIT.getMessage());
        }
    }
}
