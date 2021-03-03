package blackjack.domain;

import java.util.Arrays;
import java.util.function.Predicate;

public enum Status {
    BURST(score -> 21 < score),
    BLACKJACK(score -> 21 == score),
    HIT(score -> 21 > score);

    private final Predicate<Integer> predicate;

    Status(Predicate<Integer> predicate) {
        this.predicate = predicate;
    }

    public static Status evaluateScore(int score) {
        return Arrays.stream(Status.values())
            .filter(status -> status.predicate.test(score))
            .findAny()
            .orElseThrow(IllegalArgumentException::new);
    }
}
