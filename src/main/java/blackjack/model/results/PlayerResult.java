package blackjack.model.results;

import blackjack.model.participants.Player;
import blackjack.vo.Money;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerResult {
    private final Map<Player, Result> results;

    public PlayerResult(Map<Player, Result> results) {
        this.results = results;
    }

    public Map<Player, Result> getResults() {
        return results;
    }

    public PlayerProfit getPlayerProfit() {
        Map<Player, Money> profit = new LinkedHashMap<>();
        results.forEach((player, result) -> profit.put(player, result.getProfit(player.getBetAmount())));
        return new PlayerProfit(profit);
    }
}
