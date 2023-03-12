package blackjack.domain.vo;

import java.math.BigDecimal;

public class Money {
    private final BigDecimal value;

    public Money(int value) {
        this.value = BigDecimal.valueOf(value);
    }

    public int getValue() {
        return value.intValue();
    }
}
