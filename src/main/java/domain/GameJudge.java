package domain;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiPredicate;

public class GameJudge {
    private final Map<WinStatus, BiPredicate<Score, Score>> statusConditions;
    private final WinStatus result;

    public GameJudge(Score totalScore, Score otherScore) {
        statusConditions = new HashMap<>();
        statusConditions.put(WinStatus.WIN, Score::isGreaterThan);
        statusConditions.put(WinStatus.LOSE, Score::isLessThan);
        statusConditions.put(WinStatus.DRAW, Score::isEqualTo);
        result = determine(totalScore, otherScore);
    }

    private WinStatus determine(Score score, Score other) {
        return statusConditions.entrySet()
                .stream()
                .filter(entry -> entry.getValue().test(score, other))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("점수 계산이 올바르지 않습니다."))
                .getKey();
    }

    public WinStatus getResult() {
        return result;
    }
}
