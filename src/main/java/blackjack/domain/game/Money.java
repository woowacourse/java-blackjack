package blackjack.domain.game;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Money money = (Money) o;
        return Objects.equals(value, money.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public Integer getValue() {
        return value.intValue();
    }
}
