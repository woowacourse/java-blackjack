package domain;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import vo.GameResult;

public class GameSummary {
    private final Map<User, GameResult> userResults;

    public GameSummary(Map<User, GameResult> userResults) {
        this.userResults = new LinkedHashMap<>(userResults);
    }

    public BigDecimal getUserProfit(User user) {
        return userResults.get(user).calculateProfit(user.getBetAmount());
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
