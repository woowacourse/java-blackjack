package blackjack.domain.player;

import blackjack.domain.player.exception.InvalidMoneyAmountException;
import blackjack.domain.player.exception.InvalidMoneyUnitException;

import java.util.Objects;

public final class Money {

    private static final int UNIT = 10;
    private static final int MIN_AMOUNT = 1_000;
    private static final int MAX_AMOUNT = 1_000_000;

    private final int amount;

    private Money(final int amount) {
        this.amount = amount;
    }

    public static Money zero() {
        return new Money(0);
    }

    public Money multiplyBy(final double rate) {
        int moneyAmount = (int) (amount * rate);
        return new Money(moneyAmount);
    }

    public Money sumWith(final Money addedMoney) {
        return new Money(amount + addedMoney.amount);
    }

    public static Money from(final int amount) {
        validate(amount);
        return new Money(amount);
    }

    private static void validate(final int amount) {
        validateAmount(amount);
        validateUnit(amount);
    }

    private static void validateAmount(final int amount) {
        if (amount < MIN_AMOUNT || amount > MAX_AMOUNT) {
            throw new InvalidMoneyAmountException();
        }
    }

    private static void validateUnit(final int amount) {
        if (amount % UNIT != 0) {
            throw new InvalidMoneyUnitException();
        }
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
