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
        return new Profit((bettingAmount.getBettingAmount()
                .multiply(new BigDecimal(result.getBenefitRatio()))));
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public Profit add(Profit other) {
        return new Profit(this.profit.add(other.profit));
    }

    public Profit negate() {
        return new Profit(this.profit.negate());
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
