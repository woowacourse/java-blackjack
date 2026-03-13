package domain;

import view.mesage.ErrorMessage;

public class BettingValidator {
    public static BettingValidator of() {
        return new BettingValidator();
    }

    public void validateBetAmount(String betInput) {
        if (betInput == null) {
            throw new IllegalArgumentException(ErrorMessage.EMPTY_BET_AMOUNT_INPUT.getMessage());
        }
        if (!betInput.matches("[1-9][0-9]*")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_BET_AMOUNT.getMessage());
        }
    }
}
