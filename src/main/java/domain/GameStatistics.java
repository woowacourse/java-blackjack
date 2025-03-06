package domain;

import java.util.Map;

public class GameStatistics {
    private final Map<String, GameResult> results;

    public GameStatistics(Map<String, GameResult> results) {
        this.results = results;
    }

    public int getDealerWinCount() {
        return (int) results.values().stream()
                .filter(result -> result == GameResult.LOSE)
                .count();
    }

    public int getDealerLoseCount() {
        return (int) results.values().stream()
                .filter(result -> result == GameResult.WIN)
                .count();
    }

    public int getDealerDrawCount() {
        return (int) results.values().stream()
                .filter(result -> result == GameResult.DRAW)
                .count();
    }
}
