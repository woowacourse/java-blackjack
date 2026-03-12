package model.game;

import model.game.exception.BettingUnitMismatchException;
import model.game.exception.UnderMinimumAmountException;

public final class BettingAmount {
    private static final int DIVIDE_UNIT = 100;
    private static final int MINIMUM_BATTING_AMOUNT = 1000;

    private final int amount;

    private BettingAmount(int amount) {
        this.amount = amount;
    }

    public static BettingAmount from(int amount) {
        validateAmount(amount);

        return new BettingAmount(amount);
    }

    private static void validateAmount(int amount) {
        validateMinimumAmount(amount);
        validateDivideByUnit(amount);
    }

    private static void validateDivideByUnit(int amount) {
        if (amount % DIVIDE_UNIT != 0) {
            throw new BettingUnitMismatchException(amount, DIVIDE_UNIT);
        }
    }

    private static void validateMinimumAmount(int amount) {
        if (amount < MINIMUM_BATTING_AMOUNT) {
            throw new UnderMinimumAmountException(amount, MINIMUM_BATTING_AMOUNT);
        }
    }

    public int getAmount() {
        return amount;
    }
}
