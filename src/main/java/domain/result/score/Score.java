package domain.result.score;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Score {
    private final Integer value;

    public Score(int value) {
        this.value = value;
    }

    public static Score of(int value) {
        if (value >= ScoreCache.MIN && value <= ScoreCache.MAX) {
            return ScoreCache.get(value);
        }

        return new Score(value);
    }

    public Score plus(Score operand) {
        return Score.of(this.value + operand.value);
    }

    public boolean isBiggerThan(Score compared) {
        return this.value > compared.value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return Objects.equals(value, score.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    private static class ScoreCache {
        private static final int MIN = 0;
        private static final int MAX = 21;

        private static Map<Integer, Score> scoreCache = new HashMap<>();

        static {
            for (int i = MIN; i <= MAX; i++) {
                scoreCache.put(i, new Score(i));
            }
        }

        private static Score get(int value) {
            return scoreCache.get(value);
        }
    }
}
