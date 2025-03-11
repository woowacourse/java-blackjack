package blackjack.domain;

import blackjack.view.WinningType;
import java.util.List;

public class ProfitCalculator {
    public int calculatePlayerProfit(final WinningType winningType, final int bettingAmount) {
        return winningType.multiplyProfitRate(bettingAmount);
    }

    public int calculateDealerProfit(final List<Integer> playerProfits) {
        return 0;
    }
}
