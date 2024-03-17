package blackjack.domain.profit;

import java.util.Objects;

public class Profit {

    private final double value;

    public Profit(double value) {
        this.value = value;
    }

    public Profit(BetAmount betAmount) {
        this(betAmount.getValue());
    }

    public Profit multiply(double value) {
        return new Profit(this.value * value);
    }

    public Profit add(Profit other) {
        return new Profit(this.value + other.value);
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
        return Double.compare(value, profit.value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
