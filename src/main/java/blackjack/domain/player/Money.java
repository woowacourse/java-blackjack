package blackjack.domain.player;

import blackjack.domain.player.exception.InvalidMoneyAmountException;
import blackjack.domain.player.exception.InvalidMoneyUnitException;

public final class Money {

    private static final int UNIT = 10;
    private static final int MIN_AMOUNT = 1_000;
    private static final int MAX_AMOUNT = 1_000_000;

    private static final Money zero = new Money(0);

    private final int amount;

    private Money(int amount) {
        this.amount = amount;
    }

    public static Money zero() {
        return zero;
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
}
