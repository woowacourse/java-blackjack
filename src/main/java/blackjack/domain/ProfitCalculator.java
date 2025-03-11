package blackjack.domain;

import blackjack.view.WinningType;
import java.util.List;

public class ProfitCalculator {
    public int calculatePlayerProfit(final WinningType winningType, final int bettingAmount) {
        return winningType.multiplyProfitRate(bettingAmount);
    }

    public int calculateDealerProfit(final List<Integer> playerProfits) {
        int profit = 0;
        for (final int playerProfit : playerProfits) {
            profit += playerProfit;
        }
        return -profit;
    }
}
