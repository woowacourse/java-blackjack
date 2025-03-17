package blackjack.model.betting;

import java.util.Objects;

public class Profit {

    private final int profit;

    public Profit(int profit) {
        this.profit = profit;
    }

    public static Profit of(BetAmount betAmount, MatchResult matchResult) {
        int stake = betAmount.getStake();
        return new Profit((int) (stake * matchResult.getProfitRate()));
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
        return Objects.hashCode(profit);
    }

    public int getProfit() {
        return profit;
    }
}
