package blackjack.domain;

import java.util.Arrays;
import java.util.function.IntPredicate;

public enum Status {
    BURST(score -> Game.WINNING_NUMBER < score),
    BLACKJACK(score -> Game.WINNING_NUMBER == score),
    HIT(score -> Game.WINNING_NUMBER > score);

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
