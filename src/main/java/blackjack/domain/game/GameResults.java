package blackjack.domain.game;

import java.util.HashMap;
import java.util.Map;

public class GameResults {
    private final Map<String, GameResult> playersResults;

    GameResults() {
        this.playersResults = new HashMap<>();
    }

    public void put(String name, GameResult gameResult) {
        playersResults.put(name, gameResult);
    }

    public Map<String, GameResult> getPlayersResults() {
        return playersResults;
    }

    public Map<GameResult, Integer> getDealerResult() {
        Map<GameResult, Integer> dealerResults = new HashMap<>();
        playersResults.values().forEach(gameResult -> {
                    GameResult reversedResult = gameResult.reverse();
                    dealerResults.put(reversedResult, dealerResults.getOrDefault(reversedResult, 0) + 1);
                }
        );
        return dealerResults;
    }
}
