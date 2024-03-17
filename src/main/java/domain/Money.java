package domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Money {
    private final BigDecimal value;

    public Money(final String value) {
        this.value = new BigDecimal(value);
    }

    public BigDecimal getValue() {
        return value;
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
}
