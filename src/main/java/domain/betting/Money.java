package domain.betting;

import domain.betting.exception.ErrorMessage;
import domain.betting.exception.MoneyException;

public record Money(
        double money
) {

    private static final double MONEY_BOUND = 0.0;
    private static final double DEALER_PROFIT_TIMES = -1.0;

    public static Money from(String money) {
        validateMoneyIsNumber(money);
        return Money.from(Double.parseDouble(money));
    }

    public static Money from(double money) {
        validateMoneyIsNegative(money);
        return new Money(money);
    }

    public Money applyBettingRate(BettingRate rate) {
        return new Money(money * rate.bettingRate());
    }

    public Money getMoney() {
        return new Money(this.money);
    }

    public Money addMoney(Money other) {
        return new Money(other.money + this.money);
    }

    public Money reverseMoney() {
        return new Money(this.money * DEALER_PROFIT_TIMES);
    }

    private static void validateMoneyIsNumber(String money) {
        try {
            Double.parseDouble(money);
        } catch (NumberFormatException e) {
            throw new MoneyException(ErrorMessage.BETTING_MONEY_IS_NOT_STRING);
        }
    }

    private static void validateMoneyIsNegative(double money) {
        if (money < MONEY_BOUND) {
            throw new MoneyException(ErrorMessage.BETTING_MONEY_IS_NOT_NEGATIVE);
        }
    }

}
