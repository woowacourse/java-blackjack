package blackjack.domain.participant;

import blackjack.exception.ExceptionMessage;

public class BettingAmount {

    private static final int MIN_AMOUNT = 5_000;
    private static final int MAX_AMOUNT = 200_000;

    private final int bettingAmount;

    public BettingAmount(int bettingAmount) {
        validateRange(bettingAmount);
        this.bettingAmount = bettingAmount;
    }

    private void validateRange(int bettingAmount) {
        if (isOutOfRange(bettingAmount)) {
            throw new IllegalArgumentException(ExceptionMessage.BETTING_AMOUNT_OUT_OF_RANGE.getMessage());
        }
    }

    private boolean isOutOfRange(int bettingAmount) {
        return bettingAmount < MIN_AMOUNT || bettingAmount > MAX_AMOUNT;
    }
}
