package domain.bet;

import domain.result.WinLossResult;

public class Profit {
    private static final int DRAW_PROFIT = 0;
    private static final double BLACKJACK_BONUS = 1.5;
    private static final int WIN_BONUS = 2;

    int profit;

    public Profit(final WinLossResult winLossResult, final int betMoney) {
        this.profit = computeProfit(winLossResult, betMoney);
    }

    private int computeProfit(final WinLossResult winLossResult, final int betMoney) {
        if(winLossResult == WinLossResult.WIN) {
            return applyWinBonus(betMoney);
        }
        if(winLossResult == WinLossResult.BLACKJACK_WIN) {
            return applyBlackJackBonus(betMoney);
        }
        if(winLossResult == WinLossResult.LOSS) {
            return applyLossPenalty(betMoney);
        }
        return DRAW_PROFIT;
    }

    private int applyBlackJackBonus(int betMoney) {
        return (int) (betMoney * BLACKJACK_BONUS - betMoney);
    }

    private int applyWinBonus(int betMoney) {
        return betMoney * WIN_BONUS - betMoney;
    }

    private int applyLossPenalty(int betMoney) {
        return -betMoney;
    }

    public int getProfit() {
        return profit;
    }
}
