package blackjack.domain.player;

import blackjack.domain.player.exception.InvalidMoneyAmountException;
import blackjack.domain.player.exception.InvalidMoneyUnitException;

import java.util.Objects;

public final class Money {

    private static final int UNIT = 10;
    private static final int MIN_AMOUNT = 1_000;
    private static final int MAX_AMOUNT = 1_000_000;

    private final int amount;

    private Money(int amount) {
        this.amount = amount;
    }

    public static Money zero() {
        return new Money(0);
    }

    public static Money multiply(Money money, double rate) {
        int moneyAmount = (int) (money.amount * rate);
        return new Money(moneyAmount);
    }

    public static Money sum(Money firstMoney, Money secondMoney) {
        return new Money(firstMoney.amount + secondMoney.amount);
    }

    public static Money from(int amount) {
        validate(amount);
        return new Money(amount);
    }

    private static void validate(int amount) {
        validateAmount(amount);
        validateUnit(amount);
    }

    private static void validateAmount(int amount) {
        if (amount < MIN_AMOUNT || amount > MAX_AMOUNT) {
            throw new InvalidMoneyAmountException();
        }
    }

    private static void validateUnit(int amount) {
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
