package blackjack.model.card;

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

    public Score plus(final Score score) {
        return Score.from(value + score.value);
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

    public boolean greaterThan(final Score criterion) {
        return value > criterion.value;
    }

    public int getValue() {
        return value;
    }

    private static class ScoreCache {
        static final int MIN_VALUE = 1;
        static final int MAX_VALUE = 21;
        static final Map<Integer, Score> cache = IntStream.range(MIN_VALUE, MAX_VALUE + 1)
                .boxed()
                .collect(toMap(identity(), Score::new));

        private ScoreCache() {
        }
    }
}
