package domain;

import exception.ErrorMessage;

public class BetAmount {
    private final long amount;

    public BetAmount(Long amount){
        validateNegative(amount);
        this.amount = amount;
    }

    public BetAmount(String moneyInput) {
        amount = parseInput(moneyInput);
    }

    private long parseInput(String input) {
        try {
            return Long.parseLong(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MONEY.getMessage());
        }
    }

    private void validateNegative(long value) {
        if (value <= 0) {
            throw new IllegalArgumentException(ErrorMessage.NEGATIVE_MONEY.getMessage());
        }
    }

    public long getAmount() {
        return amount;
    }
}
