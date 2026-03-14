package domain;

import common.ErrorMessage;

public record BetAmount(
        int betAmount
) {
    public static BetAmount of(String betAmountValue) {
        int betAmount = parseBetAmount(betAmountValue);
        validatePositiveNumber(betAmount);
        return new BetAmount(betAmount);
    }

    private static void validatePositiveNumber(int betAmount) {
        if (betAmount <= 0) {
            throw new IllegalArgumentException(ErrorMessage.ZERO_MINUS_MONEY.getMessage());
        }
    }

    private static int parseBetAmount(String betAmountValue) {
        try {
            return Integer.parseInt(betAmountValue);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.ONLY_NUMBER.getMessage());
        }
    }

    public static BetAmount empty() {
        return new BetAmount(0);
    }

    public boolean isBetPlaced() {
        return betAmount != 0;
    }
}

