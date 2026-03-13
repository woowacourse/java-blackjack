package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class GameResult {

    private final Map<String, Integer> result;

    public GameResult() {
        this.result = new LinkedHashMap<>();
    }

    public void add(String userName, int profit) {
        result.put(userName, profit);
    }

    public int getDealerProfit() {
        return -1 * result.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public Set<Map.Entry<String, Integer>> getEntries() {
        return result.entrySet();
    }
}
