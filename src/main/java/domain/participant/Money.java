package domain.participant;

import utils.ExceptionMessages;

public class Money {

    public static final int ZERO_MONEY = 0;
    private final int money;

    public Money(final int money) {
        validatePositiveMoney(money);
        this.money = money;
    }

    private void validatePositiveMoney(int money) {
        if (money <= ZERO_MONEY) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_POSITIVE_MONEY_ERROR);
        }
    }

    public int multiplyDividendRate(double dividendRate){
        return (int)(money * dividendRate);
    }
}
