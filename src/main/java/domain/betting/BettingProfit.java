package domain.betting;


import domain.result.WinningStatus;

public class BettingProfit {

    private static final int WINNING_RATE = 100;
    private static final int DRAW_RATE = 0;
    private static final int LOSS_RATE = -100;
    private static final int BLACKJACK_RATE = 150;

    private final long profit;

    private BettingProfit(long profit) {
        this.profit = profit;
    }

    public static BettingProfit of(WinningStatus winningStatus, BetAmount betAmount) {
        if (winningStatus.isBlackjack()) {
            return new BettingProfit(betAmount.applyRate(BLACKJACK_RATE));
        }

        if (winningStatus.isWin()) {
            return new BettingProfit(betAmount.applyRate(WINNING_RATE));
        }

        if (winningStatus.isDraw()) {
            return new BettingProfit(betAmount.applyRate(DRAW_RATE));
        }

        return new BettingProfit(betAmount.applyRate(LOSS_RATE));
    }

    public static BettingProfit from(long profit) {
        return new BettingProfit(profit);
    }

    public long getProfit() {
        return profit;
    }

}
