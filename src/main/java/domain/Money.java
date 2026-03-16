package domain;

import java.math.BigDecimal;

public class Money {

    private final BigDecimal amount;

    private Money(BigDecimal amount) {
        this.amount = amount;
    }

    public static Money of(long amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public Money add(Money added) {
        return new Money(this.amount.add(added.amount));
    }

    public long amount() {
        return amount.longValue();
    }
}
