package domain.bet;

import domain.result.WinLossResult;

public class BetMoney {

    public static final int DRAW_PROFIT = 0;
    private final double amount;

    public BetMoney(final double amount) {
        this.amount = amount;
    }

    public BetMoney computeProfit(final WinLossResult winLossResult) {
        if(winLossResult == WinLossResult.WIN) {
            return applyWinBonus();
        }
        if(winLossResult == WinLossResult.BLACKJACK_WIN) {
            return applyBlackJackBonus();
        }
        if(winLossResult == WinLossResult.LOSS) {
            return applyLossPenalty();
        }
        return new BetMoney(DRAW_PROFIT);
    }

    private BetMoney applyBlackJackBonus() {
        return new BetMoney(amount * 1.5 - amount);
    }

    private BetMoney applyWinBonus() {
        return new BetMoney(amount * 2 - amount);
    }

    private BetMoney applyLossPenalty() {
        return new BetMoney(-amount);
    }

    public double getAmount() {
        return amount;
    }

}
