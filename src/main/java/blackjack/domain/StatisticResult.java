package blackjack.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StatisticResult {

    public static final long DEFAULT_VALUE = 0L;
    public static final int INCREASE_COUNT = 1;
    private final Map<String, Result> playerNameAneResult;

    public StatisticResult(Map<String, Result> playerNameAneResult) {
        this.playerNameAneResult = playerNameAneResult;
    }

    public Map<Result, Long> aggregateDealerResultAndCount() {
        Map<Result, Long> resultAndCount = new HashMap<>();
        Arrays.stream(Result.values()).forEach(result -> {
            resultAndCount.put(result, DEFAULT_VALUE);
        });

        playerNameAneResult.values().forEach((result) -> {
            Result replacedResult = result.replaceWinWithLose();
            resultAndCount.put(replacedResult, resultAndCount.get(replacedResult) + INCREASE_COUNT);
        });
        return Collections.unmodifiableMap(resultAndCount);
    }

    public Map<String, Result> getPlayerNameAneResult() {
        return Collections.unmodifiableMap(playerNameAneResult);
    }
}
