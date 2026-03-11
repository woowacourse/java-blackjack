package blackjack.domain.money;

import java.util.Objects;

public class Money {

    private final int value;

    public Money(int value) {
        this.value = value;
    }

    public Money add(Money money) {
        return new Money(value + money.value);
    }

    public Money multiply(double multiplier) {
        return new Money((int) (value * multiplier));
    }

    public Money negate() {
        return new Money(-value);
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return getValue() == money.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getValue());
    }
}
