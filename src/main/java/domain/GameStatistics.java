package domain;

import static domain.GameResult.DRAW;
import static domain.GameResult.LOSE;
import static domain.GameResult.WIN;

import java.util.Collections;
import java.util.Map;

public class GameStatistics {
    private final Map<String, GameResult> results;

    public GameStatistics(Map<String, GameResult> results) {
        this.results = results;
    }

    public int getDealerWinCount() {
        return getDealerResultCount(WIN);
    }

    public int getDealerLoseCount() {
        return getDealerResultCount(LOSE);
    }

    public int getDealerDrawCount() {
        return getDealerResultCount(DRAW);
    }

    private int getDealerResultCount(GameResult gameResult) {
        return (int) results.values().stream()
                .filter(result -> result == gameResult)
                .count();
    }

    public Map<String, GameResult> getResults() {
        return Collections.unmodifiableMap(results);
    }
}
