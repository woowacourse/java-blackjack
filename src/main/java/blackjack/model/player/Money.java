package blackjack.model.player;

import java.math.BigDecimal;
import java.util.Objects;

public final class Money {

    private final BigDecimal amount;

    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    public Money negate() {
        return new Money(amount.negate());
    }

    public Money multiply(BigDecimal factor) {
        return new Money(amount.multiply(factor));
    }

    public String amount() {
        return amount.toPlainString();
    }

    public Money add(Money other) {
        return new Money(amount.add(other.amount));
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
