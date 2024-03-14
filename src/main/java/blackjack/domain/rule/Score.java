package blackjack.domain.rule;

import java.util.Objects;

public final class Score implements Comparable<Score> {

    private static class ScoreCache {

        static final int low = 0;
        static final int high = 30;
        static final Score[] cache;
        static Score[] archivedCache;

        static {
            final int size = (high - low) + 1;
            archivedCache = new Score[size];

            int j = low;
            for(int i = 0; i < archivedCache.length; i++) {
                archivedCache[i] = new Score(j++);
            }

            cache = archivedCache;
        }

        private ScoreCache() {
        }
    }

    private static final Score BLACKJACK_VALUE = Score.from(21);
    private final int value;

    public Score(final int score) {
        validateRange(score);
        this.value = score;
    }

    public static Score from(final int value) {
        if (value >= ScoreCache.low && value <= ScoreCache.high) {
            return ScoreCache.archivedCache[value];
        }

        return new Score(value);
    }

    private void validateRange(final int value) {
        if (value < 0) {
            throw new IllegalStateException("점수가 음수일 수 없습니다.");
        }
    }

    public Score plus(final Score other) {
        return Score.from(value + other.value);
    }

    public Score minus(final Score other) {
        return Score.from(value - other.value);
    }

    public boolean isBlackjack() {
        return this.equals(BLACKJACK_VALUE);
    }

    public boolean isBust() {
        return this.compareTo(BLACKJACK_VALUE) > 0;
    }

    public int toInt() {
        return value;
    }

    @Override
    public int compareTo(final Score o) {
        return Integer.compare(value, o.value);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Score score = (Score) o;
        return value == score.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
