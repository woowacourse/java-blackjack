package blackjack.domain;

import blackjack.domain.betting.BettingAmount;
import blackjack.view.WinningType;
import java.util.List;

public class ProfitCalculator {
    public int calculatePlayerProfit(final WinningType winningType, final BettingAmount bettingAmount) {
        return winningType.multiplyProfitRate(bettingAmount);
    }

    public int calculateDealerProfit(final List<Integer> playersProfit) {
        return playersProfit.stream()
                .mapToInt(playerProfit -> -playerProfit)
                .sum();
    }
}
