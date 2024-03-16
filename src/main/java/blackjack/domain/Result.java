package blackjack.domain;

import java.util.Map;
import java.util.stream.Collectors;

public class Result {

    private final Map<Player, ResultStatus> results;

    public Result(Map<Player, ResultStatus> results) {
        this.results = results;
    }

    public Map<ResultStatus, Long> calculateDealerResult() {
        return results.values().stream()
                .collect(Collectors.groupingBy(this::convertResultDealerSide, Collectors.counting()));
    }

    public Map<Player, ResultStatus> getResults() {
        return results;
    }

    private ResultStatus convertResultDealerSide(ResultStatus result) {
        if (result == ResultStatus.WIN) {
            return ResultStatus.LOSE;
        }
        if (result == ResultStatus.LOSE) {
            return ResultStatus.WIN;
        }
        return ResultStatus.DRAW;
    }

}
