package domain;

import static exception.ErrorMessage.MONEY_INVALID_RANGE;
import static exception.ErrorMessage.MONEY_INVALID_UNIT;

public class Money {
    public static final int MINIMUM_BET_AMOUNT = 1_000;
    public static final int MAXIMUM_BET_AMOUNT = 300_000;
    public static final int MONEY_DIVIDE_UNIT = 10;

    private final int betAmount;

    public Money(int betAmount) {
        validate(betAmount);
        this.betAmount = betAmount;
    }

    private void validate(int betAmount) {
        validateRange(betAmount);
        validateUnit(betAmount);
    }

    private void validateRange(int betAmount) {
        if (betAmount < MINIMUM_BET_AMOUNT || betAmount > MAXIMUM_BET_AMOUNT) {
            throw new IllegalArgumentException(MONEY_INVALID_RANGE.getMessage(MINIMUM_BET_AMOUNT, MAXIMUM_BET_AMOUNT));
        }
    }

    private void validateUnit(int betAmount) {
        if (betAmount % MONEY_DIVIDE_UNIT != 0) {
            throw new IllegalArgumentException(MONEY_INVALID_UNIT.getMessage(MONEY_DIVIDE_UNIT));
        }
    }

    public int getBetAmount() {
        return betAmount;
    }
}
