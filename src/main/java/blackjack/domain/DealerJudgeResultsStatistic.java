package blackjack.domain;

import static blackjack.domain.JudgeResult.LOSE;
import static blackjack.domain.JudgeResult.PUSH;
import static blackjack.domain.JudgeResult.WIN;

import java.util.LinkedHashMap;
import java.util.Map;

public class DealerJudgeResultsStatistic {

    private final Map<JudgeResult, Integer> countByJudgeResult = new LinkedHashMap<>();

    private DealerJudgeResultsStatistic(int winCount, int pushCount, int loseCount) {
        countByJudgeResult.put(WIN, winCount);
        countByJudgeResult.put(PUSH, pushCount);
        countByJudgeResult.put(LOSE, loseCount);
    }

    public static DealerJudgeResultsStatistic from(PlayerJudgeResults playerJudgeResults) {
        int winCount = playerJudgeResults.collectCountByJudgeResult(LOSE);
        int pushCount = playerJudgeResults.collectCountByJudgeResult(PUSH);
        int loseCount = playerJudgeResults.collectCountByJudgeResult(WIN);
        return new DealerJudgeResultsStatistic(winCount, pushCount, loseCount);
    }

    public int getCountBy(JudgeResult judgeResult) {
        return countByJudgeResult.get(judgeResult);
    }
}
