package blackjack.domain.player;

import blackjack.domain.player.exception.InvalidMoneyAmountException;

public final class Money {

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
        validateAmount(amount);
        return new Money(amount);
    }

    private static void validateAmount(int amount) {
        if (amount < MIN_AMOUNT || amount > MAX_AMOUNT) {
            throw new InvalidMoneyAmountException();
        }
    }

    public int getAmount() {
        return amount;
    }
}
