package domain.betiing;


import domain.result.WinningStatus;

public class BettingProfit {

    private static final double WINNING_ODDS = 1;
    private static final double DRAW_ODDS = 0;
    private static final double LOSS_ODDS = -1;
    private static final double BLACKJACK_ODDS = 1.5;

    private final double profit;

    private BettingProfit(double profit) {
        this.profit = profit;
    }

    public static BettingProfit of(WinningStatus winningStatus, BetAmount betAmount) {
        int amount = betAmount.amount();
        if (winningStatus.equals(WinningStatus.BLCAKJACK)) {
            return new BettingProfit(calculateFinalProfit(amount, BLACKJACK_ODDS));
        }

        if (winningStatus.equals(WinningStatus.WIN)) {
            return new BettingProfit(calculateFinalProfit(amount, WINNING_ODDS));
        }

        if (winningStatus.equals(WinningStatus.DRAW)) {
            return new BettingProfit(calculateFinalProfit(amount, DRAW_ODDS));
        }
        return new BettingProfit(calculateFinalProfit(amount, LOSS_ODDS));
    }

    public static BettingProfit from(double profit) {
        return new BettingProfit(profit);
    }


    private static double calculateFinalProfit(int amount, double odds) {
        return amount * odds;
    }
    public double getProfit() {
        return profit;
    }

}
