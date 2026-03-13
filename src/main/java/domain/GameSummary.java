package domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import vo.GameResult;

public class GameSummary {
    private final Map<User, GameResult> userResults;
    private final Map<User, Long> betAmounts;

    public GameSummary(Map<User, GameResult> userResults, Map<User, Long> betAmounts) {
        this.userResults = new LinkedHashMap<>(userResults);
        this.betAmounts = new LinkedHashMap<>(betAmounts);
    }

    public long getUserProfit(User user) {
        return userResults.get(user).calculateProfit(betAmounts.get(user));
    }

    public long getDealerProfit() {
        return -userResults.keySet().stream()
                .mapToLong(this::getUserProfit)
                .sum();
    }

    public Map<User, GameResult> getUserResults() {
        return Collections.unmodifiableMap(userResults);
    }
}
