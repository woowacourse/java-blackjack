package domain.result;

import java.util.Arrays;
import java.util.function.BiFunction;

import static domain.BlackjackGame.BLACKJACK_SCORE;

public enum GameResultStatus {

    WIN("승", GameResultStatus::firstScoreGraterThanSecondScore, ResultProfitRatio.WIN),
    PUSH("무", GameResultStatus::firstScoreEqualToSecondScore, ResultProfitRatio.PUSH),
    LOSE("패", (standardTarget, comparisonTarget) -> firstScoreGraterThanSecondScore(comparisonTarget, standardTarget), ResultProfitRatio.LOSE);

    private final String value;
    private final BiFunction<Integer, Integer, Boolean> match;
    private final ResultProfitRatio resultProfitRatio;

    GameResultStatus(final String value, final BiFunction<Integer, Integer, Boolean> match, final ResultProfitRatio resultProfitRatio) {
        this.value = value;
        this.match = match;
        this.resultProfitRatio = resultProfitRatio;
    }

    public static GameResultStatus comparedTo(final Integer standardTarget, final Integer comparisonTarget) {
        return Arrays.stream(values())
                     .filter(status -> status.match.apply(standardTarget, comparisonTarget))
                     .findFirst()
                     .orElse(LOSE);
    }

    private static boolean firstScoreGraterThanSecondScore(Integer firstScore, Integer secondScore) {
        return firstScore <= BLACKJACK_SCORE
                && (secondScore > BLACKJACK_SCORE || (secondScore < firstScore));
    }

    private static boolean firstScoreEqualToSecondScore(Integer firstScore, Integer secondScore) {
        return (secondScore > BLACKJACK_SCORE && firstScore > BLACKJACK_SCORE) || (secondScore == firstScore);
    }

    public String getValue() {
        return value;
    }

    public ResultProfitRatio getResultProfitRatio() {
        return resultProfitRatio;
    }
}
