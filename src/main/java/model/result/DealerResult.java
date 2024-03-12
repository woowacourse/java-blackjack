package model.result;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class DealerResult {

    private static final Integer INITIAL_COUNT = 1;

    private final String name;

    private final Map<ResultStatus, Integer> result;

    private DealerResult(String name, Map<ResultStatus, Integer> result) {
        this.name = name;
        this.result = Collections.unmodifiableMap(result);
    }

    public static DealerResult from(GameResult gameResult) {
        Map<ResultStatus, Integer> result = new EnumMap<>(ResultStatus.class);
        ParticipantScore dealerScore = gameResult.getDealerScore();

        for (ParticipantScore playerScore : gameResult.getPlayersScore()) {
            ResultStatus resultStatus = gameResult.decideResultStatus(dealerScore, playerScore);
            result.merge(resultStatus, INITIAL_COUNT, Integer::sum);
        }
        return new DealerResult(gameResult.dealerName(), result);
    }

    public List<ResultStatus> allStatus() {
        return Arrays.stream(ResultStatus.values())
            .filter(result::containsKey)
            .toList();
    }

    public Integer statusCount(ResultStatus resultStatus) {
        return result.getOrDefault(resultStatus, 0);
    }

    public String getName() {
        return name;
    }
}
