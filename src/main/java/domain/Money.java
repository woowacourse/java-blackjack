package domain;

import static exception.ErrorMessage.MONEY_INVALID_RANGE;
import static exception.ErrorMessage.MONEY_INVALID_UNIT;

/**
 * 돈(금액)을 관리하는 클래스
 */
public class Money {
    private static final int MINIMUM_BET_AMOUNT = 1_000;
    private static final int MAXIMUM_BET_AMOUNT = 300_000;
    private static final int MONEY_DIVIDE_UNIT = 10;
    private static final double MONEY_MULTIPLY_PERCENT = 1.5;

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
            throw new IllegalArgumentException(MONEY_INVALID_RANGE.getMessage());
        }
    }

    private void validateUnit(int betAmount) {
        if (betAmount % MONEY_DIVIDE_UNIT != 0) {
            throw new IllegalArgumentException(MONEY_INVALID_UNIT.getMessage());
        }
    }

    public int multiply() {
        return (int) (betAmount * MONEY_MULTIPLY_PERCENT);
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
