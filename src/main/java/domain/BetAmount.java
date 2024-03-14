package domain;

import constants.ErrorCode;
import exception.InvalidBetAmountException;
import exception.OutOfRangeBetAmount;
import java.util.Objects;

public class BetAmount {

    private static final int UNIT = 10;
    private static final int MIN_RANGE = 10;
    private static final int MAX_RANGE = 100_000_000;

    private final long amount;

    public BetAmount(final long amount) {
        validate(amount);
        this.amount = amount;
    }


    public Amount loseAmount() {
        return new Amount(-amount);
    }

    public Amount blackJackWinAmount() {
        return new Amount(amount + (amount / 2));
    }

    public Amount winAmount() {
        return new Amount(amount);
    }

    public Amount tieAmount() {
        return new Amount(0);
    }

    private void validate(final long value) {
        validateRange(value);
        validateUnit(value);
    }

    private void validateUnit(final long value) {
        if (value % UNIT != 0) {
            throw new InvalidBetAmountException(ErrorCode.INVALID_BET_AMOUNT);
        }
    }

    private void validateRange(final long value) {
        if (value < MIN_RANGE || MAX_RANGE < value) {
            throw new OutOfRangeBetAmount(ErrorCode.INVALID_BET_AMOUNT);
        }
    }

    @Override
    public boolean equals(final Object target) {
        if (this == target) {
            return true;
        }
        if (!(target instanceof BetAmount betAmount)) {
            return false;
        }
        return Objects.equals(amount, betAmount.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount);
    }

    @Override
    public String toString() {
        return String.valueOf(amount);
    }
}
