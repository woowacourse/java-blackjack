package blackjack.domain.game;

import java.math.BigDecimal;

public class Money {

    private final BigDecimal value;

    public Money(final int value) {
        this.value = BigDecimal.valueOf(value);
    }

    public Money(final BigDecimal value) {
        this.value = value;
    }

    public Money add(final Money money) {
        return new Money(value.add(money.value));
    }

    public Money multiple(final BigDecimal times) {
        return new Money(value.multiply(times));
    }

    public Integer getValue() {
        return value.intValue();
    }
}
