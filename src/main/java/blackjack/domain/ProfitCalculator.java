package blackjack.domain;

import blackjack.domain.betting.BettingAmount;
import blackjack.domain.gambler.Name;
import blackjack.view.WinningType;
import java.util.Map;

public class ProfitCalculator {
    public int calculatePlayerProfit(final WinningType winningType, final BettingAmount bettingAmount) {
        int winningAmount = winningType.calculateWinningAmount(bettingAmount);
        return bettingAmount.calculateProfit(winningAmount);
    }

    public int calculateDealerProfit(final Map<Name, Integer> playersProfit) {
        return playersProfit.values()
                .stream()
                .mapToInt(Math::negateExact)
                .sum();
    }
}
