package blackjack.domain.game;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackJackResult {
    private final List<ResultType> results;

    public BlackJackResult(final List<ResultType> results) {
        this.results = results;
    }

    public BlackJackResult(final ResultType results) {
        this(List.of(results));
    }

    public Map<ResultType, Integer> getResults() {
        Map<ResultType, Integer> resultsCount = new LinkedHashMap<>();
        results.forEach(result -> resultsCount.put(result, resultsCount.getOrDefault(result, 0) + 1));
        return resultsCount;
    }
}
