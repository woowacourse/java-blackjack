package domain.money;

import static exception.ErrorMessage.INVALID_BETTING_AMOUNT;

public class BettingMoney extends Money {
    private BettingMoney(long amount) {
        super(amount);
    }

    public static BettingMoney from(long amount) {
        validate(amount);
        return new BettingMoney(amount);
    }

    private static void validate(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(INVALID_BETTING_AMOUNT.getMessage());
        }
    }
}
