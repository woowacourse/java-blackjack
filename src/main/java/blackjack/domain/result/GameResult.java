package blackjack.domain.result;

import java.util.LinkedHashMap;
import java.util.Map;

import blackjack.domain.gamer.Player;

public class GameResult {

    private final Map<Player, Result> result;

    public GameResult(final Map<Player, Result> result) {
        this.result = result;
    }

    public BettingResult calculateRevenue() {
        Map<Player, Integer> revenue = new LinkedHashMap<>();
        int dealerRevenue = 0;

        for (Map.Entry<Player, Result> player : result.entrySet()) {
            int playerRevenue = player.getValue().calculateRevenue(player.getKey().getMoney());
            revenue.put(player.getKey(), playerRevenue);
            dealerRevenue += player.getValue().calculateReverseRevenue(playerRevenue);
        }

        return new BettingResult(dealerRevenue, revenue);
    }
}
