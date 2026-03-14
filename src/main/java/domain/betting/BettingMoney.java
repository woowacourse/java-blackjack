package domain.betting;

import domain.betting.exception.ErrorMessage;
import domain.betting.exception.MoneyException;


public record BettingMoney(
        int amount
) {

    private static final int BET_BOUND = 0;

    public static BettingMoney bet(String bettingMoney) {
        validateNumericFormat(bettingMoney);
        return BettingMoney.bet(Integer.parseInt(bettingMoney));
    }

    public static BettingMoney bet(int bettingMoney) {
        validateBettingMoneyIsNotNegative(bettingMoney);
        return new BettingMoney(bettingMoney);
    }

    public Profit withRate(double rate) {
        return new Profit((int) (this.amount * rate));
    }

    public BettingMoney getBettingProfit() {
        return new BettingMoney(this.amount);
    }

    private static void validateNumericFormat(String money) {
        try {
            Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new MoneyException(ErrorMessage.BETTING_MONEY_IS_NOT_STRING);
        }
    }

    private static void validateBettingMoneyIsNotNegative(int money) {
        if (money < BET_BOUND) {
            throw new MoneyException(ErrorMessage.BETTING_MONEY_IS_NOT_NEGATIVE);
        }
    }
}
