package blackjack.model;

import blackjack.exception.ErrorMessage;

public class BettingAmount {
    private final double money;

    public BettingAmount(double money) {
        validateMoney(money);
        this.money = money;
    }

    public Profit calculateProfit(GameResult gameResult) {
        return new Profit(money * gameResult.getPayout());
    }

    public double getMoney() {
        return money;
    }

    private void validateMoney(double money) {
        if (money <= 0) {
            throw new IllegalArgumentException(ErrorMessage.AMOUNT_NOT_ZERO.getMessage());
        }
    }
}
