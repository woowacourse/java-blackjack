package blackjack.domain.result;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class DealerResult {

    private final Map<Result, Integer> result;

    public DealerResult(List<UserResult> userResults) {
        this.result = new EnumMap<>(Result.class);
        calculateFinalCount(userResults);
    }

    private void calculateFinalCount(List<UserResult> userResults) {
        for (UserResult userResult : userResults) {
            addCount(Result.swap(userResult.getResult()));
        }
    }

    private void addCount(Result result) {
        this.result.put(result, this.result.getOrDefault(result, 0) + 1);
    }

    public Map<Result, Integer> getResult() {
        return Collections.unmodifiableMap(result);
    }
}
