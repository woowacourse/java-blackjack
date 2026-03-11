package domain.betting;

import domain.betting.exception.ErrorMessage;
import domain.betting.exception.MoneyException;

public record Money(
        double money
) {

    public static Money from(String money) {
        validateMoneyIsNumber(money);
        validateMoneyIsNegative(Double.parseDouble(money));
        return new Money(Double.parseDouble(money));
    }

    public Money changeMoney(double times) {
        return new Money(money * times);
    }

    public Money getMoney() {
        return new Money(this.money);
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
