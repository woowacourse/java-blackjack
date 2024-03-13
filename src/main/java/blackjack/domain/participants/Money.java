package blackjack.domain.participants;

import java.util.Objects;

public class Money {

    public static final int INITIAL_MONEY = 0;

    private final int value;

    public Money(int value) {
        this.value = value;
    }

    public Money() {
        this.value = INITIAL_MONEY;
    }

    public Money add(Money otherMoney) {
        return new Money(this.value + otherMoney.value);
    }

    public Money subtract(Money otherMoney) {
        return new Money(this.value - otherMoney.value);
    }

    public Money multiply(float number) {
        return new Money(Math.round(this.value * number));
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money = (Money) o;
        return value == money.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
