package blackjack.domain.card;

import java.util.HashMap;
import java.util.Map;

public class Score implements Comparable<Score> {

    private static final int ACE_VALUE_DIFF = 10;
    public static final int BLACKJACK = 21;
    private static final String NEGATIVE_SCORE_NOT_ALLOWED_EXCEPTION = "점수는 음수가 될 수 없습니다!";

    private final int value;

    private Score(final int value) {
        this.value = value;
    }

    public static Score valueOf(final int value) {
        validateNotNegative(value);
        return ScoreCache.getCache(value);
    }

    public Score add(Score anotherScore) {
        int addedValue = this.value + anotherScore.value;
        return ScoreCache.getCache(addedValue);
    }

    public Score incrementAceIfNotBust() {
        int incrementedValue = this.value + ACE_VALUE_DIFF;
        if (incrementedValue <= BLACKJACK) {
            return Score.valueOf(incrementedValue);
        }
        return this;
    }

    public int toInt() {
        return value;
    }

    private static void validateNotNegative(int value) {
        if (value < 0) {
            throw new IllegalArgumentException(NEGATIVE_SCORE_NOT_ALLOWED_EXCEPTION);
        }
    }

    @Override
    public int compareTo(Score o) {
        return Integer.compare(this.value, o.value);
    }

    @Override
    public String toString() {
        return "Score{" + "value=" + value + '}';
    }

    private static class ScoreCache {
        static Map<Integer, Score> cache = new HashMap<>();

        static Score getCache(final int value) {
            return cache.computeIfAbsent(value, Score::new);
        }
    }
}
