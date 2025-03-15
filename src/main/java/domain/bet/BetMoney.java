package domain.bet;

import domain.result.WinLossResult;

public class BetMoney {

    private static final int DRAW_PROFIT = 0;
    private static final double BLACKJACK_BONUS = 1.5;
    private static final int WIN_BONUS = 2;

    private final int amount;

    public BetMoney(final int amount) {
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
        return new BetMoney((int) (amount * BLACKJACK_BONUS - amount));
    }

    private BetMoney applyWinBonus() {
        return new BetMoney(amount * WIN_BONUS - amount);
    }

    private BetMoney applyLossPenalty() {
        return new BetMoney(-amount);
    }

    public double getAmount() {
        return amount;
    }

}
