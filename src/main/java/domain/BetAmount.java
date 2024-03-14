package domain;

import constants.ErrorCode;
import exception.InvalidBetAmountException;
import java.util.Objects;

public class BetAmount {

    private final long amount;

    public BetAmount(final long amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(final long value) { // TODO 블랙잭일 경우 1.5배를 해줘야하기에 10원 단위인지 확인필요
        if (value <= 0) {
            throw new InvalidBetAmountException(ErrorCode.INVALID_BET_AMOUNT);
        }
    }

    public Amount loseAmount() {
        return new Amount(-amount);
    }

    public Amount winAmount() {
        return new Amount(amount);
    }

    public Amount tieAmount() {
        return new Amount(0);
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
