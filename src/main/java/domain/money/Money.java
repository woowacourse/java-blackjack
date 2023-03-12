package domain.money;

import java.math.BigDecimal;
import java.util.Objects;

public class Money {

    private final BigDecimal amount;

    public Money(final int amount) {
        this.amount = BigDecimal.valueOf(amount);
    }

    public Money(final BigDecimal amount) {
        this(amount.intValue());
    }

    public Money multiply(final BigDecimal amount) {
        return new Money(this.amount.multiply(amount));
    }

    public int getAmount() {
        return amount.intValue();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money = (Money) o;
        return Objects.equals(amount, money.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                '}';
    }
}
