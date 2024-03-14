package blackjack.domain;

import java.util.Objects;

public class Profit {

    private final int profit;
    private final boolean isPositive;

    public Profit(int profit) {
        this(profit, true);
    }

    private Profit(int profit, boolean isPositive) {
        this.profit = profit;
        this.isPositive = isPositive;
    }

    public Profit multiple(double ratio) {
        return new Profit((int) Math.floor(profit * ratio), isPositive);
    }

    public Profit inverse() {
        return new Profit(profit, !isPositive);
    }

    public boolean isPositive() {
        return isPositive;
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
        return profit == profit1.profit && isPositive == profit1.isPositive;
    }

    @Override
    public int hashCode() {
        return Objects.hash(profit, isPositive);
    }

    public int getProfit() {
        if (isPositive) {
            return profit;
        }
        return profit * -1;
    }
}
