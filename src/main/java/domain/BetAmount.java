package domain;

import common.ErrorMessage;

public record BetAmount(
        int value
) {
    public static BetAmount of(int value) {
        validateOverThanZero(value);
        return new BetAmount(value);
    }

    private static void validateOverThanZero(int value) {
        if (value < 1) {
            throw new IllegalArgumentException(ErrorMessage.ZERO_MINUS_MONEY.getMessage());
        }
    }

    public static BetAmount empty() {
        return new BetAmount(0);
    }

    public boolean isBetPlaced() {
        return value != 0;
    }
}

