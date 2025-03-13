package model.result;

import java.util.Map;

public class GameResults {
    private final Map<String, GameResult> gameResults;

    public GameResults(final Map<String, GameResult> gameResults) {
        this.gameResults = gameResults;
    }

    public GameResult getGameResultByName(final String name) {
        return gameResults.get(name);
    }

    public int calculateDealerResultCount(final GameResult gameResult) {
        return (int) gameResults.values().stream()
                .filter(value -> GameResult.getOppositeResult(value).equals(gameResult))
                .count();
    }
}
