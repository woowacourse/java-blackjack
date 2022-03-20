package domain.participant;

import java.math.BigDecimal;

public class Money {
    private final BigDecimal value;

    public Money(int value) {
        this.value = new BigDecimal(value);
    }

    public BigDecimal getValue() {
        return value;
    }
}
