package blackjack.domain;

import java.util.Objects;

public class Profit {
    private static final int MIN_PROFIT = -10_000_000;
    private static final int MAX_PROFIT = 10_000_000;

    private final double Profit;

    private Profit(double bat) {
        this.Profit = bat;
    }

    public static Profit from(double profit) {
        validateProfit(profit);
        return new Profit(profit);
    }

    private static void validateProfit(double profit) {
        if (profit < MIN_PROFIT || profit > MAX_PROFIT) {
            throw new IllegalArgumentException("수익은 최소 " + MIN_PROFIT + "부터 "
                    + MAX_PROFIT + "까지 가능합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        blackjack.domain.Profit that = (blackjack.domain.Profit) o;
        return Objects.equals(Profit, that.Profit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Profit);
    }

    public Double getProfit() {
        return Profit;
    }
}
