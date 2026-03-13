package domain;

import common.ErrorMessage;

public record BetAmount(
        int betAmount
) {
    public static BetAmount of(int betAmount) {
        if (betAmount <= 0) {
            throw new IllegalArgumentException(ErrorMessage.ZERO_MINUS_MONEY.getMessage());
        }
        return new BetAmount(betAmount);
    }

    public static BetAmount empty() {
        return new BetAmount(0);
    }
}

