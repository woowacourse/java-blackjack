package blackjack.domain.result;

import blackjack.domain.user.User;

import java.util.List;

public class Profit {
    private static final Profit ZERO_PROFIT = new Profit(0);

    private final int profit;

    public Profit(int profit) {
        this.profit = profit;
    }

    public static Profit of(User player, User dealer) {
        return player.calculateProfit(dealer);
    }

    public static Profit sum(List<Profit> profits) {
        Profit profitSum = ZERO_PROFIT;

        for (Profit profit : profits) {
            profitSum = profitSum.plus(profit);
        }

        return profitSum;
    }

    private Profit plus(Profit other) {
        return new Profit(this.profit + other.profit);
    }

    public Profit opposite() {
        return new Profit(profit * -1);
    }

    public int getProfit() {
        return profit;
    }
}
