package blackjack.domain;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class GameResult {

    private final Map<String, BigDecimal> result;

    public GameResult() {
        this.result = new LinkedHashMap<>();
    }

    public void add(String userName, BigDecimal profit) {
        result.put(userName, profit);
    }

    public BigDecimal getDealerProfit() {
        return result.values().stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .negate();
    }

    public Set<Map.Entry<String, BigDecimal>> getEntries() {
        return result.entrySet();
    }
}
