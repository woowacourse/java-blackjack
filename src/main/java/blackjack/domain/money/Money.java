package blackjack.domain.money;

import java.util.Objects;

public class Money {
    private final long amount;

    public Money(long amount) {
        this.amount = amount;
    }

    public Money multiply(double multiplier) {
        return new Money(Math.round(amount * multiplier));
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Money money)) {
            return false;
        }

        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
