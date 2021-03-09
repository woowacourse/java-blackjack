package blackjack.domain;

import java.util.Arrays;
import java.util.function.IntPredicate;

public enum Status {
    BURST(Rule::isBurst),
    BLACKJACK(Rule::isBlackJack),
    HIT(Rule::isHit);

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

    static class Rule {
        private static final int WINNING_NUMBER = 21;

        static boolean isBurst(int score) {
            return WINNING_NUMBER < score;
        }

        static boolean isBlackJack(int score) {
            return WINNING_NUMBER == score;
        }

        static boolean isHit(int score) {
            return WINNING_NUMBER > score;
        }
    }
}
