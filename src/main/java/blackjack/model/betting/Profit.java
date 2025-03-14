package blackjack.model.betting;

import blackjack.model.MatchResult;
import java.util.Objects;

public class Profit {

    private final int profit;

    public Profit(int profit) {
        this.profit = profit;
    }

    public static Profit of(BetAmount betAmount, MatchResult matchResult) {
        int stake = betAmount.getStake();
        if (matchResult == MatchResult.BLACKJACK) {
            return new Profit((int) (1.5 * stake));
        }
        if (matchResult == MatchResult.WIN) {
            return new Profit(stake);
        }
        if (matchResult == MatchResult.DRAW) {
            return new Profit(0);
        }
        return new Profit(stake * -1);
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
