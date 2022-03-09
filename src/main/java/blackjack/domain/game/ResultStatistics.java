package blackjack.domain.game;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class ResultStatistics {
    private final Map<ResultType, ResultCount> stats = new EnumMap<>(ResultType.class);

    public ResultStatistics() {
        Arrays.stream(ResultType.values())
                .forEach(this::initializeResult);
    }

    private void initializeResult(ResultType resultType) {
        stats.put(resultType, new ResultCount());
    }

    public ResultCount getCountOf(ResultType resultType) {
        return stats.get(resultType);
    }

    public void incrementCountOf(ResultType resultType) {
        ResultCount count = stats.get(resultType);
        count.increment();
    }

    @Override
    public String toString() {
        return "ResultStatistics{" + "stats=" + stats + '}';
    }
}
