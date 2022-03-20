package blackjack.domain.money;

import blackjack.domain.game.ResultType;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Money {
    private static final BigDecimal BLACKJACK_PROFIT_RATE = new BigDecimal("1.5");

    private final BigDecimal value;

    private Money(int value) {
        this.value = new BigDecimal(value);
    }

    private Money(BigDecimal value) {
        this.value = value;
    }

    public static Money from(int betted) {
        return new Money(betted);
    }

    public static Money createBlackjackProfit(Money money) {
        BigDecimal profit = money.value.multiply(BLACKJACK_PROFIT_RATE).setScale(0, RoundingMode.HALF_UP);
        return new Money(profit);
    }

    public static Money createAsNegative(Money money) {
        return new Money(money.value.negate());
    }

    public Money calculateProfit(ResultType resultType) {
        int calculatedValue = resultType.profitCalculator.apply(value.intValue());
        return new Money(calculatedValue);
    }

    public Money add(Money operandMoney) {
        return new Money(this.value.add(operandMoney.value));
    }

    public Money subtract(Money operandMoney) {
        return new Money(this.value.subtract(operandMoney.value));
    }

    public boolean isNegative() {
        return value.compareTo(BigDecimal.ZERO) < 0;
    }

    public int getIntValue() {
        return value.intValue();
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

        return value.equals(money.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return "Money{" +
                "value=" + value +
                '}';
    }
}
