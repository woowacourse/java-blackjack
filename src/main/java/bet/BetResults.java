package bet;

import java.util.LinkedHashMap;
import java.util.Map;
import player.Player;

public class BetResults {
    private final Map<Player, BetResult> betResults;

    public BetResults() {
        this.betResults = new LinkedHashMap<>();
    }

    public void addBetResult(Player player, BetResult betResult) {
        betResults.put(player, betResult);
    }

    public int calculateDealerBettingResult() {
        return - betResults.values().stream()
                .mapToInt(BetResult::getAmount)
                .sum();
    }

    public Map<Player, BetResult> getBetResults() {
        return betResults;
    }
}
