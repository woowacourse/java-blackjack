package domain.money;

import static exception.ErrorMessage.INVALID_BETTING_AMOUNT;

public record BettingMoney(Money money) {

    public static BettingMoney from(long amount) {
        validate(amount);
        return new BettingMoney(new Money(amount));
    }

    public static BettingMoney from(Money money) {
        validate(money.amount());
        return new BettingMoney(money);
    }

    private static void validate(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(INVALID_BETTING_AMOUNT.getMessage());
        }
    }
}
