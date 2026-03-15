package blackjack.domain.profit;

import blackjack.domain.money.Money;
import blackjack.domain.participant.Player;
import blackjack.domain.result.GameResults;
import blackjack.domain.wager.Wagers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProfitCalculator {

    public ProfitResults calculate(GameResults gameResults, Wagers wagers) {
        final Map<Player, Money> profits = new LinkedHashMap<>();

        List<Player> players = wagers.allPlayer();
        for (Player player : players) {
            Money playerProfit = gameResults.profitOf(player, wagers.wagerOf(player));
            profits.put(player, playerProfit);
        }
        return new ProfitResults(profits);
    }
}
