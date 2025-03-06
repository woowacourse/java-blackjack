package domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum FinalResult {
    WIN("승", (sumOfRank, otherSumOfRank) -> sumOfRank > otherSumOfRank),
    LOSE("패", (sumOfRank, otherSumOfRank) -> sumOfRank < otherSumOfRank),
    DRAW("무", Integer::equals);

    private final String title;
    private final BiPredicate<Integer, Integer> condition;

    FinalResult(final String title, final BiPredicate<Integer, Integer> condition) {
        this.title = title;
        this.condition = condition;
    }

    public static FinalResult findBySumOfRank(final int sumOfRank, int otherSumOfRank) {
        return Arrays.stream(FinalResult.values())
                .filter(finalResult -> finalResult.condition.test(sumOfRank, otherSumOfRank))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("비 정상적인 상태입니다."));
    }

    public String getTitle() {
        return title;
    }
}
