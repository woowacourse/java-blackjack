package domain.money;

import java.math.BigDecimal;
import java.util.Objects;

public class Money {

    private final BigDecimal amount;

    public static Money of(int amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public static Money zero() {
        return new Money(BigDecimal.ZERO);
    }

    private Money(BigDecimal amount) {
        this.amount = amount;
    }

    public Money multiply(BigDecimal ratio) {
        return new Money(amount.multiply(ratio));
    }

    public Money add(Money money) {
        return new Money(amount.add(money.amount));
    }

    public Money negate() {
        return new Money(amount.negate());
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount.compareTo(money.amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
