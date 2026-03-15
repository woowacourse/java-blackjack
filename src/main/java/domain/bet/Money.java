package domain.bet;

import static message.ErrorMessage.BETTING_MONEY_MUST_BE_MULTIPLE_OF_100;
import static message.ErrorMessage.BETTING_MONEY_NOT_AVAILABLE;

import java.util.Objects;

public class Money {

    private static final long ZERO = 0;
    private static final long HUNDRED_WON = 100;

    private final long amount;

    private Money(long amount) {
        this.amount = amount;
    }

    public static Money bet(long amount) {
        validateBettingMoney(amount);
        return new Money(amount);
    }

    public static Money zero() {
        return new Money(ZERO);
    }

    public long amount() {
        return amount;
    }

    private static void validateBettingMoney(long amount) {
        if (amount <= ZERO) {
            throw new IllegalArgumentException(BETTING_MONEY_NOT_AVAILABLE.getMessage());
        }

        if (amount % HUNDRED_WON != ZERO) {
            throw new IllegalArgumentException(BETTING_MONEY_MUST_BE_MULTIPLE_OF_100.getMessage());
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Money money)) {
            return false;
        }
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
