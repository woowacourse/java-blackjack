package blackjack.domain;

import static blackjack.view.WinningType.BLACKJACK_WIN;
import static blackjack.view.WinningType.DEFEAT;
import static blackjack.view.WinningType.WIN;

import blackjack.view.WinningType;

public class ProfitCalculator {
    public int calculatePlayerProfit(final WinningType winningType, final int bettingAmount) {
        if (winningType == BLACKJACK_WIN) {
            return (int) (bettingAmount * 1.5);
        }
        if (winningType == WIN) {
            return bettingAmount * 2;
        }
        if (winningType == DEFEAT) {
            return bettingAmount * -1;
        }
        return bettingAmount;
    }
}
