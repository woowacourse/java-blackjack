package blackjack.domain.player;

import java.util.Objects;

public class Profit {
    private final int value;

    public Profit(double value) {
        this.value = (int) value;
    }

    public Profit addProfit(Profit other) {
        return new Profit(this.value + other.value);
    }

    public Profit negate() {
        return new Profit(-this.value);
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Profit profit = (Profit) o;
        return value == profit.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
