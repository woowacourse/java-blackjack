package domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import vo.GameResult;

public class GameSummary {
    private final Map<String, GameResult> userResults;

    public GameSummary(Map<String, GameResult> userResults) {
        this.userResults = new LinkedHashMap<>(userResults);
    }

    public int getDealerWinCount() {
        return (int) userResults.values().stream()
                .filter(result -> result == GameResult.LOSE || result == GameResult.BUST)
                .count();
    }

    public int getDealerLoseCount() {
        return (int) userResults.values().stream()
                .filter(result -> result == GameResult.WIN || result == GameResult.PUSH)
                .count();
    }

    public Map<String, GameResult> getUserResults() {
        return Collections.unmodifiableMap(userResults);
    }
}