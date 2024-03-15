package blackjack.domain.gameresult;

import java.util.Objects;

public class Profit {
    private final double profit;

    private Profit(double bat) {
        this.profit = bat;
    }

    public static Profit from(double profit) {
        return new Profit(profit);
    }

    public double getProfit() {
        return profit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        blackjack.domain.gameresult.Profit that = (blackjack.domain.gameresult.Profit) o;
        return Objects.equals(profit, that.profit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profit);
    }
}
