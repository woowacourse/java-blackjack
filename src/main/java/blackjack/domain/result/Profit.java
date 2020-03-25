package blackjack.domain.result;

import java.util.Objects;

public class Profit {

    private final int profit;

    private Profit(int profit) {
        this.profit = profit;
    }

    public static Profit from(int profit) {
        return new Profit(profit);
    }

    public static Profit from(double profit) {
        return new Profit((int) profit);
    }

    public Profit minus(Profit playerProfit) {
        return Profit.from(profit - playerProfit.profit);
    }

    public int getProfit() {
        return profit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profit profit = (Profit) o;
        return this.profit == profit.profit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(profit);
    }
}
