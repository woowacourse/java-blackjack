package blackjack.domain.betting;

import blackjack.domain.result.WinningResult;

public class EarningRateCalculator {

    public double calculate(WinningResult winningResult) {
        if (winningResult == WinningResult.BLACKJACK) {
            return 1.5;
        }
        if (winningResult == WinningResult.WIN) {
            return 1;
        }
        if(winningResult == WinningResult.LOSE) {
            return -1;
        }
        return 0;
    }

}
