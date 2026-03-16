package domain.common;

import java.math.BigDecimal;

public class Money {

    private final BigDecimal amount;

    Money(BigDecimal amount) {
        this.amount = amount;
    }

    public static Money of(long amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public long amount() {
        return amount.longValue();
    }
}
