package blackjack.domain.bet;

import java.util.Objects;

public class Profit {

    private final double profit;

    public Profit(double profit) {
        this.profit = profit;
    }

    public double get() {
        return profit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Profit profit1 = (Profit) o;
        return profit == profit1.profit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(profit);
    }

    @Override
    public String toString() {
        return "Profit{" +
                "profit=" + profit +
                '}';
    }
}
