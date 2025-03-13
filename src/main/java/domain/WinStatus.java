package domain;


import java.util.Arrays;
import java.util.function.BiPredicate;

public enum WinStatus {

    WIN("승", Score::isGreaterThan),
    LOSE("패", Score::isLessThan),
    DRAW("무승부", Score::isEqualTo);

    private final String status;
    private final BiPredicate<Score, Score> condition;

    WinStatus(String status, BiPredicate<Score, Score> condition) {
        this.status = status;
        this.condition = condition;
    }

    public static WinStatus determine(Score score, Score other) {
        return Arrays.stream(values())
                .filter(value -> value.condition.test(score, other))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("점수 계산이 올바르지 않습니다."));
    }

    public String getStatus() {
        return status;
    }
}
