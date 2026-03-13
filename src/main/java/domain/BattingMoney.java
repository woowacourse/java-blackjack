package domain;

import exception.ExceptionMessage;

public record BattingMoney(int money) {
    private static final int MINIMUM_MONEY = 1000;
    private static final int MAXIMUM_MONEY = 1000000;

    public BattingMoney {
        validateAmountRange(money);
    }

    public void validateAmountRange(int money) {
        if (money < MINIMUM_MONEY || money > MAXIMUM_MONEY) {
            throw new IllegalArgumentException(ExceptionMessage.BATTING_MONEY_RANGE.getMessage());
        }
    }

    public int calculateBattingProfit(double profit) {
        return (int) (profit * money);
    }
}
