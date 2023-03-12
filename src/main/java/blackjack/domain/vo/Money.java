package blackjack.domain.vo;

import java.math.BigDecimal;
import java.util.Objects;

public class Money {
    private final BigDecimal value;

    public Money(BigDecimal value) {
        this.value = value;
    }

    public Money(int value) {
        this(BigDecimal.valueOf(value));
    }

    public int getValue() {
        return value.intValue();
    }

    public Money multiply(double ratio) {
        BigDecimal multiply = this.value.multiply(BigDecimal.valueOf(ratio));
        return new Money(multiply.intValue());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Money money = (Money) o;
        return Objects.equals(value, money.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Money{" +
                "value=" + value +
                '}';
    }
}
