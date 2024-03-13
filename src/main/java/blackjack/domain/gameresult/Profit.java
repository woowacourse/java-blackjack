package blackjack.domain.gameresult;

import java.util.Objects;

public class Profit {
    private static final double MIN_PROFIT_RATE = -1;
    private static final double MAX_PROFIT_RATE = 1.5;

    private final double Profit;

    private Profit(double bat) {
        this.Profit = bat;
    }

    public static Profit from(double profit) {
        validateProfit(profit);
        return new Profit(profit);
    }

    private static void validateProfit(double profit) {
        double minProfit = MIN_PROFIT_RATE * Batting.maxBat();
        double maxProfit = MAX_PROFIT_RATE * Batting.maxBat();

        if (profit < minProfit || profit > maxProfit) {
            throw new IllegalArgumentException(
                    String.format("수익은 최소 %.0f부터 %.0f까지 가능합니다.", minProfit, maxProfit)
            );
        }
    }

    public Double getProfit() {
        return Profit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        blackjack.domain.gameresult.Profit that = (blackjack.domain.gameresult.Profit) o;
        return Objects.equals(Profit, that.Profit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Profit);
    }
}
