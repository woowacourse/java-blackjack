package domain.betting;

import domain.betting.exception.ErrorMessage;
import domain.betting.exception.MoneyException;

public record Money(
        int money
) {

    private static final int MONEY_BOUND = 0;

    public static Money from(String money) {
        validateMoneyIsNumber(money);
        return Money.from(Integer.parseInt(money));
    }

    public static Money from(int money) {
        validateMoneyIsNegative(money);
        return new Money(money);
    }

    public Money applyBettingRate(BettingRate rate) {
        return new Money((int) (this.money * rate.bettingRate()));
    }

    public Money getMoney() {
        return new Money(this.money);
    }

    public Money addMoney(Money other) {
        return new Money(other.money + this.money);
    }

    public Money reverseMoney() {
        return new Money(-this.money);
    }

    private static void validateMoneyIsNumber(String money) {
        try {
            Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new MoneyException(ErrorMessage.BETTING_MONEY_IS_NOT_STRING);
        }
    }

    private static void validateMoneyIsNegative(int money) {
        if (money < MONEY_BOUND) {
            throw new MoneyException(ErrorMessage.BETTING_MONEY_IS_NOT_NEGATIVE);
        }
    }



}
