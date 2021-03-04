package blackjack.domain;

import java.util.Arrays;
import java.util.function.IntPredicate;

public enum Status {
    BURST(score -> 21 < score),
    BLACKJACK(score -> 21 == score),
    HIT(score -> 21 > score);

    private final IntPredicate predicate;

    Status(IntPredicate predicate) {
        this.predicate = predicate;
    }

    public static Status evaluateScore(int score) {
        return Arrays.stream(Status.values())
            .filter(status -> status.predicate.test(score))
            .findAny()
            .orElseThrow(IllegalArgumentException::new);
    }
}
