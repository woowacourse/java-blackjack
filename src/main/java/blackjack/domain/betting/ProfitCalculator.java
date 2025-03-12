package blackjack.domain.betting;

import blackjack.domain.gambler.Name;
import blackjack.domain.game.WinningType;
import java.util.Map;

public class ProfitCalculator {
    public int calculatePlayerProfit(final WinningType winningType, final BettingAmount bettingAmount) {
        int winningAmount = winningType.calculateWinningAmount(bettingAmount);
        return bettingAmount.subtractFrom(winningAmount);
    }

    public int calculateDealerProfit(final Map<Name, Integer> playersProfit) {
        return playersProfit.values()
                .stream()
                .mapToInt(Math::negateExact)
                .sum();
    }
}
