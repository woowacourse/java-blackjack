package dto;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import model.game.ResultStatus;

public class DealerResult {

    private static final Integer INITIAL_COUNT = 1;

    private final Map<ResultStatus, Integer> result;

    public DealerResult(Map<ResultStatus, Integer> result) {
        this.result = result;
    }

    public static DealerResult from(List<PlayerResult> playerResults) {
        Map<ResultStatus, Integer> result = new EnumMap<>(ResultStatus.class);
        for (PlayerResult playerResult : playerResults) {
            ResultStatus dealerResult = playerResult.getStatus().oppositeStatus();
            result.merge(dealerResult, INITIAL_COUNT, Integer::sum);
        }
        return new DealerResult(result);
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
