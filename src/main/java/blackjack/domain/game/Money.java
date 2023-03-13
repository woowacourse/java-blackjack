package blackjack.domain.game;

import blackjack.domain.result.Result;
import blackjack.exception.InvalidBetMoneyException;
import java.util.Objects;

public class Money {
    public static final Money ZERO = new Money(0);
    private static final int MAX_BET_MONEY = 100000;
    private static final int MIN_BET_MONEY = 10000;

    private final int value;

    private Money(int value) {
        this.value = value;
    }

    public static Money bet(int money) {
        validateBetMoney(money);
        return new Money(money);
    }

    private static void validateBetMoney(int money) {
        if (money < MIN_BET_MONEY || MAX_BET_MONEY < money) {
            throw new InvalidBetMoneyException(MIN_BET_MONEY, MAX_BET_MONEY);
        }
    }

    public Money calculateRevenue(Result result) {
        int revenue = (int) Math.round(value * result.getRateOfReturn());
        return new Money(revenue);
    }

    public Money plus(Money money) {
        return new Money(value + money.value);
    }

    public Money reverse() {
        return new Money(-this.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money = (Money) o;
        return value == money.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public int getValue() {
        return value;
    }
}
