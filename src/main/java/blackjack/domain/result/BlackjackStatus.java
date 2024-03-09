package blackjack.domain.result;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum BlackjackStatus {
    DEAD(Score::isBiggerThan),
    ALIVE((score, blackjackScore) -> blackjackScore.isBiggerThan(score)),
    BLACKJACK(Score::equals);

    private static final int BLACKJACK_NUMBER = 21;
    private static final Score BLACKJACK_SCORE = new Score(BLACKJACK_NUMBER);

    private final BiPredicate<Score, Score> condition;

    BlackjackStatus(final BiPredicate<Score, Score> condition) {
        this.condition = condition;
    }

    public static BlackjackStatus from(final Score score) {
        return Arrays.stream(values())
                .filter(blackjackStatus -> blackjackStatus.condition.test(score, BLACKJACK_SCORE))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("잘못된 점수입니다."));
    }

    public boolean isBlackjack() {
        return this == BLACKJACK;
    }

    public boolean isDead() {
        return this == DEAD;
    }
}
