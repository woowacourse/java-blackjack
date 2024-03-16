package blackjack.domain.money;

import java.util.Objects;

public class Profit {
    private final long amount;

    public Profit(long amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Profit profit)) {
            return false;
        }

        return amount == profit.amount;
    }

    public Profit add(Profit other) {
        return new Profit(amount + other.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return "Profit{" +
                "amount=" + amount +
                '}';
    }

    public long getAmount() {
        return amount;
    }
}
