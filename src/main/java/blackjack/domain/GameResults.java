package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class GameResults {
    private final Map<Player, GameResult> gameResults;

    private GameResults(Map<Player, GameResult> gameResults) {
        this.gameResults = gameResults;
    }

    public static GameResults of(Dealer dealer, Players players) {
        Map<Player, GameResult> results = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            GameResult gameResult = GameResult.matchResult(player, dealer);
            results.put(player, gameResult);
        }
        return new GameResults(results);
    }

    public Map<Player, Integer> getGameProfitResult() {
        Map<Player, Integer> results = new LinkedHashMap<>();
        for (Map.Entry<Player, GameResult> entry : gameResults.entrySet()) {
            Player player = entry.getKey();
            double ratio = entry.getValue().getRatio();
            results.put(player, player.calculateProfit(ratio));
        }
        return results;
    }
}
