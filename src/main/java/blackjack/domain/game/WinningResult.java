package blackjack.domain.game;

import blackjack.domain.betting.ProfitCalculator;
import blackjack.domain.gambler.Player;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class WinningResult {
    final Map<Player, WinningType> result;
    final ProfitCalculator profitCalculator;

    public WinningResult(final Map<Player, WinningType> result) {
        this.result = new HashMap<>(result);
        profitCalculator = new ProfitCalculator();
    }

    public int calculateDealerProfit() {
        Map<Player, Integer> playersProfit = calculatePlayersProfit();
        return profitCalculator.calculateDealerProfit(playersProfit);
    }

    public Map<Player, Integer> calculatePlayersProfit() {
        Map<Player, Integer> playersProfit = new HashMap<>();
        for (Entry<Player, WinningType> entry : result.entrySet()) {
            Player player = entry.getKey();
            WinningType winningType = entry.getValue();
            int profit = profitCalculator.calculatePlayerProfit(winningType, player.getBettingAmount());
            playersProfit.put(player, profit);
        }
        return playersProfit;
    }
}
