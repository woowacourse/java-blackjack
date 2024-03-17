package domain.manager;

import java.util.Objects;

public class Profit {

    private final double value;

    public Profit(Bet bet) {
        this(bet.getValue());
    }

    public Profit(double value) {
        this.value = value;
    }

    public Profit multiply(double multiplier) {
        return new Profit(value * multiplier);
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
