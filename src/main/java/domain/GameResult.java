package domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {
    private final Map<Player, ResultStatus> results;

    public GameResult() {
        this.results = new LinkedHashMap<>();
    }

    public void record(Player player, ResultStatus status) {
        results.put(player, status);
    }

    public Map<ResultStatus, Integer> getDealerResult() {
        Map<ResultStatus, Integer> dealerResults = new EnumMap<>(ResultStatus.class);

        for (ResultStatus status : results.values()) {
            ResultStatus reverseStatus = status.reverse();
            dealerResults.put(reverseStatus, dealerResults.getOrDefault(reverseStatus, 0) + 1);
        }

        return dealerResults;
    }

    public Map<Player, ResultStatus> getPlayerResult() {
        return Collections.unmodifiableMap(this.results);
    }
}
