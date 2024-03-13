package domain;

import constants.ErrorCode;
import exception.InvalidBetAmountException;

public class BetAmount {

    private final long amount;

    public BetAmount(final long amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(final long value) {
        if (value <= 0) {
            throw new InvalidBetAmountException(ErrorCode.INVALID_BET_AMOUNT);
        }
    }
}
