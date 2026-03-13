package domain;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import vo.GameResult;

public class GameSummary {
    private final Map<User, GameResult> userResults;
    private final Map<User, BigDecimal> betAmounts;

    public GameSummary(Map<User, GameResult> userResults, Map<User, BigDecimal> betAmounts) {
        this.userResults = new LinkedHashMap<>(userResults);
        this.betAmounts = new LinkedHashMap<>(betAmounts);
    }

    public BigDecimal getUserProfit(User user) {
        return userResults.get(user).calculateProfit(betAmounts.get(user));
    }

    public BigDecimal getDealerProfit() {
        return userResults.keySet().stream()
                .map(this::getUserProfit)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .negate();
    }

    public Map<User, GameResult> getUserResults() {
        return Collections.unmodifiableMap(userResults);
    }
}
