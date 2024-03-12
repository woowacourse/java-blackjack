package model.blackjackgame;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DealerResult {

    private final Map<ResultStatus, Integer> result;

    public DealerResult(Map<ResultStatus, Integer> result) {
        this.result = Map.copyOf(result);
    }

    public List<ResultStatus> allStatus() {
        return Arrays.stream(ResultStatus.values())
            .filter(result::containsKey)
            .toList();
    }

    public Integer statusCount(ResultStatus resultStatus) {
        return result.getOrDefault(resultStatus, 0);
    }
}
