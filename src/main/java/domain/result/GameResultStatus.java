package domain.result;

import domain.vo.Score;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum GameResultStatus {

    WIN("승", Score::isWin, ResultProfitRatio.WIN),
    PUSH("무", Score::isDraw, ResultProfitRatio.PUSH),
    LOSE("패", Score::isLose, ResultProfitRatio.LOSE);

    private final String value;
    private final BiFunction<Score, Score, Boolean> match;
    private final ResultProfitRatio resultProfitRatio;

    GameResultStatus(final String value, final BiFunction<Score, Score, Boolean> match, final ResultProfitRatio resultProfitRatio) {
        this.value = value;
        this.match = match;
        this.resultProfitRatio = resultProfitRatio;
    }

    public static GameResultStatus comparedTo(final Score standardTarget, final Score comparisonTarget) {
        return Arrays.stream(values())
                     .filter(status -> status.match.apply(standardTarget, comparisonTarget))
                     .findFirst()
                     .orElse(LOSE);
    }

    public String getValue() {
        return value;
    }

    public ResultProfitRatio getResultProfitRatio() {
        return resultProfitRatio;
    }
}
