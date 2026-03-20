package domain;

import exception.BettingAmountOutOfRangeException;
import exception.InvalidBettingUnitException;

public class Money {
    private final int money;
    private static final int BETTING_AMOUNT_UNIT = 1_000;
    private static final int MIN_BETTING_AMOUNT = 1_000;
    private static final int MAX_BETTING_AMOUNT = 100_000;

    public Money(int money) {
        validate(money);
        this.money = money;
    }

    private void validate(int input) {
        if (input < MIN_BETTING_AMOUNT || MAX_BETTING_AMOUNT < input) {
            throw new BettingAmountOutOfRangeException(MIN_BETTING_AMOUNT, MAX_BETTING_AMOUNT);
        }
        if (input % BETTING_AMOUNT_UNIT != 0) {
            throw new InvalidBettingUnitException(BETTING_AMOUNT_UNIT);
        }
    }
}
