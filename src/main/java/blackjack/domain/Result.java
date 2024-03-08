package blackjack.domain;

import java.util.Map;
import java.util.stream.Collectors;

public class Result {

    private final Map<Player, ResultStatus> results;

    public Result(Map<Player, ResultStatus> results) {
        this.results = results;
    }

    public Map<Player, ResultStatus> getResults() {
        return results;
    }

    public Map<ResultStatus, Long> calculateDealerResult() {
        return results.values().stream()
                .collect(Collectors.groupingBy(ResultStatus::swap, Collectors.counting()));
    }

}
