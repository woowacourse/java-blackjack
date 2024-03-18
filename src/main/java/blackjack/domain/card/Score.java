package blackjack.domain.card;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Score {
    private static final int MINIMUM_SCORE = 0;
    private static final int MAXIMUM_SCORE = 31;
    private static final int BONUS_SCORE_OF_ACE = 10;
    private static final int BLACKJACK_SCORE = 21;

    private static Map<Integer, Score> cachedScore = IntStream.rangeClosed(MINIMUM_SCORE, MAXIMUM_SCORE)
            .boxed()
            .collect(Collectors.toMap(Function.identity(), Score::new));

    private final int score;

    private Score(final int score) {
        this.score = score;
    }

    public static Score initialScore() {
        return valueOf(MINIMUM_SCORE);
    }

    public static Score blackjackScore() {
        return valueOf(BLACKJACK_SCORE);
    }

    public static Score valueOf(final int score) {
        return cachedScore.get(score);
    }

    public static Score sum(final Score firstScore, final Score secondScore) {
        final int newScore = firstScore.score + secondScore.score;

        return cachedScore.computeIfAbsent(newScore, integer -> new Score(newScore));
    }

    public Score addBonusScoreIfNotBust() {
        final int addedScore = score + BONUS_SCORE_OF_ACE;

        if (addedScore <= BLACKJACK_SCORE) {
            return valueOf(addedScore);
        }
        return this;
    }

    public boolean isGreaterThan(final Score other) {
        return score > other.score;
    }

    public boolean isLessThan(final Score other) {
        return score < other.score;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Score other = (Score) o;
        return score == other.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
