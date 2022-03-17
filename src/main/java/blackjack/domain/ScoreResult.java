package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;

public class ScoreResult {

    private final Map<String, Integer> playersResult;

    private ScoreResult(Map<String, Integer> playersResult){
        this.playersResult = playersResult;
    }

    public static ScoreResult from(List<Player> players, Dealer dealer) {
        Map<String, Integer> playerResults = new LinkedHashMap<>();
        playerResults.put(dealer.getName(), 0);

        for (Player player : players) {
            int profit = (int)(player.getProfit(dealer));
            playerResults.put(player.getName(), profit);
            playerResults.merge(dealer.getName(), -profit, Integer::sum);
        }
        return new ScoreResult(playerResults);
    }

    public Map<String, Integer> getPlayersResult() {
        return playersResult;
    }
}
