package blackjack.domain.player;

import java.util.Objects;

public class Profit {
    private final int value;

    public Profit(double value) {
        this.value = (int) value;
    }

    public Profit subtract(Profit playerProfit) {
        return new Profit(value - playerProfit.value);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Profit profit = (Profit) o;
        return this.value == profit.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public int getValue() {
        return value;
    }
}
