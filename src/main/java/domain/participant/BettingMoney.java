package domain.participant;

import utils.ExceptionMessages;

public final class BettingMoney {

    private static final int ZERO_MONEY = 0;

    private final int money;

    private BettingMoney(final int money) {
        validatePositiveMoney(money);
        this.money = money;
    }

    public static BettingMoney from(int money) {
        return new BettingMoney(money);
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
