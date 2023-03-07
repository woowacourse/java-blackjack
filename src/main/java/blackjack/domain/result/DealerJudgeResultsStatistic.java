package blackjack.domain.result;

import static blackjack.domain.result.JudgeResult.LOSE;
import static blackjack.domain.result.JudgeResult.PUSH;
import static blackjack.domain.result.JudgeResult.WIN;

import java.util.LinkedHashMap;
import java.util.Map;

public class DealerJudgeResultsStatistic {

    private final Map<JudgeResult, Integer> countByJudgeResult = new LinkedHashMap<>();

    private DealerJudgeResultsStatistic(final int winCount, final int pushCount, final int loseCount) {
        countByJudgeResult.put(WIN, winCount);
        countByJudgeResult.put(PUSH, pushCount);
        countByJudgeResult.put(LOSE, loseCount);
    }

    public static DealerJudgeResultsStatistic from(final PlayerJudgeResults playerJudgeResults) {
        final int winCount = playerJudgeResults.collectCountByJudgeResult(LOSE);
        final int pushCount = playerJudgeResults.collectCountByJudgeResult(PUSH);
        final int loseCount = playerJudgeResults.collectCountByJudgeResult(WIN);
        return new DealerJudgeResultsStatistic(winCount, pushCount, loseCount);
    }

    public int getCountBy(final JudgeResult judgeResult) {
        return countByJudgeResult.get(judgeResult);
    }
}
