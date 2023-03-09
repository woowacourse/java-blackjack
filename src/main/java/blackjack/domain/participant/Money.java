package blackjack.domain.participant;

import java.util.Objects;

public class Money {
    public static final Money ZERO = new Money(0);

    private final int value;

    public Money(final int value) {
        this.value = value;
    }

    public Money earn(final Money money) {
        validateEarnValueIsNegative(money);
        return new Money(this.value + money.value);
    }

    public Money spend(final Money money) {
        return new Money(this.value - money.value);
    }

    public Money times(final double value) {
        return new Money((int) (this.value * value));
    }

    public int getValue() {
        return value;
    }

    private void validateEarnValueIsNegative(final Money money) {
        if (money.value < 0) {
            throw new IllegalArgumentException("번 금액이 음수일 수 없습니다.");
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Money)) {
            return false;
        }
        final Money money = (Money) o;
        return value == money.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
