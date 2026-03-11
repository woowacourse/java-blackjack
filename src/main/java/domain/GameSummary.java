package domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import vo.GameResult;

public class GameSummary {
    private final Map<String, GameResult> userResults;
    private final Map<String, Long> betAmounts;

    public GameSummary(Map<String, GameResult> userResults, Map<String, Long> betAmounts) {
        this.userResults = new LinkedHashMap<>(userResults);
        this.betAmounts = new LinkedHashMap<>(betAmounts);
    }

    public long getUserProfit(String name) {
        return userResults.get(name).calculateProfit(betAmounts.get(name));
    }

    public long getDealerProfit() {
        return -userResults.keySet().stream()
                .mapToLong(this::getUserProfit)
                .sum();
    }

    public Map<String, GameResult> getUserResults() {
        return Collections.unmodifiableMap(userResults);
    }
}