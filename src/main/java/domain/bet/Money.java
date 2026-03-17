package domain.bet;

import static message.ErrorMessage.BETTING_MONEY_MUST_BE_MULTIPLE_OF_100;
import static message.ErrorMessage.BETTING_MONEY_NOT_AVAILABLE;

public record Money(long amount) {

    private static final long ZERO = 0;
    private static final long AMOUNT_UNIT = 100;

    public Money {
        validateRange(amount);
        validateUnit(amount);
    }

    private void validateRange(long amount) {
        if (amount < ZERO) {
            throw new IllegalArgumentException(BETTING_MONEY_NOT_AVAILABLE.getMessage());
        }
    }

    private void validateUnit(long amount) {
        if (amount % AMOUNT_UNIT != ZERO) {
            throw new IllegalArgumentException(BETTING_MONEY_MUST_BE_MULTIPLE_OF_100.getMessage());
        }
    }

    public static Money zero() {
        return new Money(ZERO);
    }
}
