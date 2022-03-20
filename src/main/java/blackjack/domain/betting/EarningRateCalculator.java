package blackjack.domain.betting;

import blackjack.domain.result.WinningResult;

public class EarningRateCalculator {

    private static final double BLACKJACK_EARNING_RATE = 1.5;
    private static final double WIN_EARNING_RATE = 1;
    private static final double LOSE_EARNING_RATE = -1;
    private static final double DRAW_EARNING_RATE = 0;

    public double calculate(WinningResult winningResult) {
        if (winningResult.isBlackjack()) {
            return BLACKJACK_EARNING_RATE;
        }
        if (winningResult.isWin()) {
            return WIN_EARNING_RATE;
        }
        if (winningResult.isLose()) {
            return LOSE_EARNING_RATE;
        }
        return DRAW_EARNING_RATE;
    }

}
