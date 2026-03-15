package domain.player;

import util.ErrorMessage;

public class Money {

    public static final int MIN_BETTING_MONEY = 0;

    private final int amount;

    public Money(int amount) {
        validateAmountRange(amount);
        this.amount = amount;
    }

    private void validateAmountRange(int amount) {
        if (amount <= MIN_BETTING_MONEY) {
            throw new IllegalArgumentException(ErrorMessage.MONEY_RANGE.getMessage());
        }
    }

    public int multiply(int value) {
        return this.amount * value;
    }

    public int getAmount() {
        return amount;
    }
}
