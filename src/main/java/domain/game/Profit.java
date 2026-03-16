package domain.game;

import domain.betting.BettingAmount;
import java.math.BigDecimal;
import java.util.Objects;

public class Profit {

    private final BigDecimal profit;

    public Profit(BigDecimal profit) {
        this.profit = profit;
    }

    public static Profit calculateProfit(GameResult result, BettingAmount bettingAmount) {
        if (result == GameResult.BLACK_JACK) {
            return new Profit((bettingAmount.getBettingAmount()
                    .multiply(new BigDecimal("1.5"))));
        }
        if (result == GameResult.WIN) {
            return new Profit(bettingAmount.getBettingAmount());
        }
        if (result == GameResult.DRAW) {
            return new Profit(BigDecimal.ZERO);
        }
        return new Profit(bettingAmount.getBettingAmount().negate());
    }

    public BigDecimal getProfit() {
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
        return this.profit.compareTo(that.profit) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(profit);
    }
}
