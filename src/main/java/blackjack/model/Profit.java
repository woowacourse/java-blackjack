package blackjack.model;

import java.util.Objects;

public class Profit {

    private final double profit;

    public Profit(double profit) {
        this.profit = profit;
    }

    public Profit negate() {
        return new Profit(-profit);
    }

    public Profit add(Profit other) {
        return new Profit(this.profit + other.profit);
    }

    public double getProfit() {
        return profit;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Profit profit1 = (Profit) o;
        return Double.compare(profit, profit1.profit) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(profit);
    }
}
