package domain.player.participant;

import java.math.BigDecimal;
import java.util.Objects;

public class Money {

    public static final Money MIN = new Money(BigDecimal.valueOf(0));
    private static final int LOSE_MONEY = -1;

    private final BigDecimal value;

    private Money(BigDecimal value) {
        this.value = value;
    }

    public static Money wons(int amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public static Money wons(double amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public BigDecimal value() {
        return value;
    }

    public Money breakEven() {
        return this;
    }

    public Money times(final double percent) {
        final BigDecimal percentage = BigDecimal.valueOf(percent);

        return new Money(value.multiply(percentage));
    }

    public Money lose() {
        return new Money(value.multiply(BigDecimal.valueOf(LOSE_MONEY)));
    }

    public Money plus(Money other) {
        return new Money(value.add(other.value));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Money money = (Money) o;
        return value.compareTo(money.value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
