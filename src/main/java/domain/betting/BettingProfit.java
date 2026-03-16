package domain.betting;


import domain.result.WinningStatus;

public class BettingProfit {

    private final long profit;

    private BettingProfit(long profit) {
        this.profit = profit;
    }

    public static BettingProfit of(WinningStatus winningStatus, BetAmount betAmount) {
        long result = betAmount.applyRate(winningStatus.getProfitRate());
        return new BettingProfit(result);
    }

    public static BettingProfit from(long profit) {
        return new BettingProfit(profit);
    }

    public long getProfit() {
        return profit;
    }

}
