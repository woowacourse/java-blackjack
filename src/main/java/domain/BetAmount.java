package domain;

import view.mesage.ErrorMessage;

public class BetAmount {
    private final Long betAmount;

    private BetAmount(String betAmount) {
        validateBetAmount(betAmount);
        this.betAmount = Long.parseLong(betAmount);
    }

    public static BetAmount of(String betAmount) {
        return new BetAmount(betAmount);
    }

    private void validateBetAmount(String betInput) {
        if (betInput == null || betInput.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.EMPTY_BET_AMOUNT_INPUT.getMessage());
        }
        if (!betInput.matches("[1-9][0-9]*")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_BET_AMOUNT.getMessage());
        }
    }

    public Long getBetAmount() {
        return betAmount;
    }
}
