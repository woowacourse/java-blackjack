package domain.betting;

import domain.betting.exception.ErrorMessage;
import domain.betting.exception.MoneyException;

public record Money(
        double money
) {

    public static Money from(String money) {
        validateMoneyIsNumber(money);
        return Money.from(Double.parseDouble(money));
    }

    public static Money from(double money) {
        validateMoneyIsNegative(money);
        return new Money(money);
    }

    public Money changeMoney(BettingRate rate) {
        return new Money(money * rate.bettingRate());
    }

    public Money getMoney() {
        return new Money(this.money);
    }

    public Money addMoney(Money other) {
        return new Money(other.money + this.money);
    }

    public Money reverseMoney() {
        return new Money(this.money * -1);
    }

    private static void validateMoneyIsNumber(String money) {
        try {
            Double.parseDouble(money);
        } catch (NumberFormatException e) {
            throw new MoneyException(ErrorMessage.BETTING_MONEY_IS_NOT_STRING);
        }
    }

    private static  void validateMoneyIsNegative(double money) {
        if (money < 0) {
            throw new MoneyException(ErrorMessage.BETTING_MONEY_IS_NOT_NEGATIVE);
        }
    }

}
