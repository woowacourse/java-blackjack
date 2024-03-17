package domain.amount;

import constants.ErrorCode;
import exception.InvalidBetAmountException;
import exception.OutOfRangeBetAmount;
import java.util.Objects;

public class BetAmount {

    private static final int UNIT = 10;
    private static final int MIN_RANGE = 10;
    private static final int MAX_RANGE = 100_000_000;

    private final long value;

    public BetAmount(final long value) {
        validate(value);
        this.value = value;
    }

    public EarnAmount loseAmount() {
        return new EarnAmount(-value);
    }

    public EarnAmount blackJackWinAmount() {
        return new EarnAmount(value + (value / 2));
    }

    public EarnAmount winAmount() {
        return new EarnAmount(value);
    }

    public EarnAmount tieAmount() {
        return new EarnAmount(0);
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
        return Objects.equals(value, betAmount.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
