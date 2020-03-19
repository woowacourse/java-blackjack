package blackjack.domain.gamer;

public class Profit {

    private final int profit;

    private Profit(final int profit) {
        this.profit = profit;
    }

    public static Profit of(int bettingMoney, double profitRate) {
        return new Profit((int) (bettingMoney * profitRate));
    }

    public static Profit from(double profit) {
        return new Profit((int) profit);
    }

    public static Profit from(int profit) {
        return new Profit(profit);
    }

    public int getProfit() {
        return profit;
    }
}
