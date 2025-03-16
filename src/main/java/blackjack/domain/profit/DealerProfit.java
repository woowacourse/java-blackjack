package blackjack.domain.profit;

public class DealerProfit {
    private final int profit;

    public DealerProfit(int profit) {
        this.profit = profit;
    }

    public static DealerProfit createWithPlayerProfit(int playerProfit) {
        if (playerProfit == 0) {
            return new DealerProfit(0);
        }
        return new DealerProfit(playerProfit * -1);
    }

    public int getProfit() {
        return profit;
    }
}
