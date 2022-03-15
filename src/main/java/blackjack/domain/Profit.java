package blackjack.domain;

import java.util.Objects;

public class Profit {

    private final int amount;

    public Profit(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Profit profit = (Profit)o;
        return amount == profit.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
