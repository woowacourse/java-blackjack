package blackjack.model.card;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

public class Score {
    private final int value;

    private Score(final int value) {
        this.value = value;
    }

    public static Score from(final int value) {
        if (value >= ScoreCache.MIN_VALUE && value <= ScoreCache.MAX_VALUE) {
            return ScoreCache.cache.get(value);
        }
        return new Score(value);
    }

    public static Score from(final List<Score> scores) {
        int sum = scores.stream()
                .mapToInt(Score::getValue)
                .sum();
        return Score.from(sum);
    }

    public Score plus(final Score score) {
        return new Score(value + score.value);
    }

    public boolean equalTo(final Score criterion) {
        return value == criterion.value;
    }

    public boolean equalToOrLessThan(final Score criterion) {
        return value <= criterion.value;
    }

    public boolean lessThan(final Score criterion) {
        return value < criterion.value;
    }

    public boolean equalToOrGreaterThan(final Score criterion) {
        return value >= criterion.value;
    }

    public boolean greaterThan(final Score criterion) {
        return value > criterion.value;
    }

    public int getValue() {
        return value;
    }

    private static class ScoreCache {
        static final int MIN_VALUE = 1;
        static final int MAX_VALUE = 22;
        static final Map<Integer, Score> cache = IntStream.range(MIN_VALUE, MAX_VALUE)
                .boxed()
                .collect(toMap(identity(), Score::new));

        private ScoreCache() {
        }
    }
}
