package blackjack.domain.participant;

import blackjack.exception.ExceptionMessage;

public class BettingAmount {

    private static final int INIT_AMOUNT = 0;
    private static final int MIN_AMOUNT = 5_000;
    private static final int MAX_AMOUNT = 200_000;
    private static final int BETTING_UNIT = 1_000;

    private final int bettingAmount;

    public static BettingAmount initial() {
        return new BettingAmount(INIT_AMOUNT);
    }

    private BettingAmount(int bettingAmount) {
        this.bettingAmount = bettingAmount;
    }

    public BettingAmount register(int bettingAmount) {
        validate(bettingAmount);
        return new BettingAmount(bettingAmount);
    }

    private void validate(int bettingAmount) {
        validateRange(bettingAmount);
        validateUnit(bettingAmount);
    }

    private void validateRange(int bettingAmount) {
        if (bettingAmount < MIN_AMOUNT || bettingAmount > MAX_AMOUNT) {
            throw new IllegalArgumentException(ExceptionMessage.BETTING_AMOUNT_OUT_OF_RANGE.getMessage());
        }
    }

    private void validateUnit(int bettingAmount) {
        if (bettingAmount % BETTING_UNIT != INIT_AMOUNT) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_BETTING_UNIT.getMessage(BETTING_UNIT));
        }
    }

    public long calculateProfit(double profitRate) {
        return Math.round(bettingAmount * profitRate);
    }
}
