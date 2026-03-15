package domain.betiing;


import domain.result.WinningStatus;

public class BettingProfit {

    private static final double WINNING_ODDS = 1;
    private static final double DRAW_ODDS = 0;
    private static final double LOSS_ODDS = -1;
    private static final double BLACKJACK_ODDS = 1.5;

    private final long profit;

    private BettingProfit(long profit) {
        this.profit = profit;
    }

    public static BettingProfit of(WinningStatus winningStatus, BetAmount betAmount) {
        if (winningStatus.equals(WinningStatus.BLCAKJACK)) {
            return new BettingProfit(betAmount.calculateProfit(BLACKJACK_ODDS));
        }

        if (winningStatus.equals(WinningStatus.WIN)) {
            return new BettingProfit(betAmount.calculateProfit(WINNING_ODDS));
        }

        if (winningStatus.equals(WinningStatus.DRAW)) {
            return new BettingProfit(betAmount.calculateProfit(DRAW_ODDS));
        }

        return new BettingProfit(betAmount.calculateProfit(LOSS_ODDS));
    }

    public static BettingProfit from(long profit) {
        return new BettingProfit(profit);
    }

    public long getProfit() {
        return profit;
    }

}
