package model.result;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import model.blackjackgame.GameScore;
import model.blackjackgame.ResultStatus;

public class DealerResult {

    private static final Integer INITIAL_COUNT = 1;

    private final Map<ResultStatus, Integer> result;

    private DealerResult(Map<ResultStatus, Integer> result) {
        this.result = Collections.unmodifiableMap(result);
    }

    public static DealerResult from(GameResult gameResult) {
        Map<ResultStatus, Integer> result = new EnumMap<>(ResultStatus.class);
        GameScore dealerScore = gameResult.getDealerScore();

        for (GameScore playerScore : gameResult.getPlayersScore()) {
            ResultStatus resultStatus = gameResult.decideResultStatus(dealerScore, playerScore);
            result.merge(resultStatus, INITIAL_COUNT, Integer::sum);
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
