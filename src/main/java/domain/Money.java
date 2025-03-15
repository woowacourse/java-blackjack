package domain;

import java.math.BigDecimal;

public class Money {
    private final BigDecimal amount;

    public Money(final String amount) {
        this.amount = new BigDecimal(amount);
    }

    private Money(BigDecimal amount) {
        this.amount = amount;
    }

    public static Money multiply(Money money, double severalTimes) {
        BigDecimal profit = money.amount.multiply(BigDecimal.valueOf(severalTimes));
        return new Money(profit);
    }

    public BigDecimal getAmount() {
        return amount.setScale(0);
    }
}
