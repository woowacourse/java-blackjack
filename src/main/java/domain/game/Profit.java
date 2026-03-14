package domain.game;

import java.util.Objects;

public class Profit {

    private final int profit;

    public Profit(int profit) {
        this.profit = profit;
    }

    public int getProfit() {
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
        Profit that = (Profit) o;
        return profit == that.profit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(profit);
    }
}
