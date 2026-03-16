package domain;

import static exception.ErrorMessage.MONEY_INVALID_RANGE;

public class Money {
    private static final int MINIMUM_BET_AMOUNT = 1_000;
    private static final int MAXIMUM_BET_AMOUNT = 300_000;

    private final int betAmount;

    public Money(int betAmount) {
        validateRange(betAmount);
        this.betAmount = betAmount;
    }

    private void validateRange(int betAmount) {
        if (betAmount < MINIMUM_BET_AMOUNT || betAmount > MAXIMUM_BET_AMOUNT) {
            throw new IllegalArgumentException(MONEY_INVALID_RANGE.getMessage());
        }
    }

    public int multiply() {
        return (int) (betAmount * 1.5);
    }

    public int add() {
        return betAmount;
    }

    public int subtract() {
        return -betAmount;
    }

    public int getBack() {
        return 0;
    }
}
