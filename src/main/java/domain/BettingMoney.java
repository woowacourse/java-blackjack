package domain;

import exception.ExceptionMessage;

public record BettingMoney(long money) {
    private static final int MINIMUM_MONEY = 0;

    public BettingMoney {
        validateAmountRange(money);
    }

    public void validateAmountRange(long money) {
        if (money <= MINIMUM_MONEY) {
            throw new IllegalArgumentException(ExceptionMessage.BATTING_MONEY_RANGE.getMessage());
        }
    }

    public long calculateBattingProfit(double profit) {
        return (long) (profit * money);
    }
}
