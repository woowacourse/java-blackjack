package blackjack.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Score {
    private static final int BONUS_SCORE_OF_ACE = 10;
    private static final int BLACKJACK_SCORE = 21;
    private static Map<Integer, Score> cachedScore = new HashMap<>();

    static {
        for (int score = 0; score < 30; score++) {
            cachedScore.put(score, new Score(score));
        }
    }

    private final int score;

    private Score(final int score) {
        this.score = score;
    }

    public static Score valueOf(final int score) {
        return cachedScore.get(score);
    }

    public static Score initialScore() {
        return cachedScore.get(0);
    }

    public static Score sum(final Score firstScore, final Score secondScore) {
        final int newScore = firstScore.score + secondScore.score;

        return cachedScore.computeIfAbsent(newScore, integer -> new Score(newScore));
    }

    public static Score getBlackjackScore() {
        return valueOf(BLACKJACK_SCORE);
    }

    public Score addBonusScoreIfNotBust() {
        int sumWithBonusScore = score + BONUS_SCORE_OF_ACE;

        if (sumWithBonusScore <= BLACKJACK_SCORE) {
            return valueOf(sumWithBonusScore);
        }
        return this;
    }

    public boolean isGreaterThan(final Score other) {
        return score > other.score;
    }

    public boolean isLessThan(final Score other) {
        return score < other.score;
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
