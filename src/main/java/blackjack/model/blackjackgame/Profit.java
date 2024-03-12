package blackjack.model.blackjackgame;

import java.util.Objects;

public final class Profit {
    private final double value;

    public Profit(double value) {
        this.value = value;
    }

    public static Profit getDefaults() {
        return new Profit(0);
    }

    public Profit sum(Profit other) {
        return new Profit(value + other.value);
    }

    public Profit reverse() {
        return new Profit(-value);
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Profit profit = (Profit) o;
        return value == profit.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
