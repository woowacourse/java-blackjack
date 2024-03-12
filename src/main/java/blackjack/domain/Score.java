package blackjack.domain;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Score {
    private static final int MIN_CACHE_SCORE = 0;
    private static final int MAX_CACHE_SCORE = 30;
    private static final Map<Integer, Score> POOL;

    static {
        POOL = IntStream.rangeClosed(MIN_CACHE_SCORE, MAX_CACHE_SCORE)
                .boxed()
                .collect(Collectors.toMap(Function.identity(), Score::new));
    }

    private final int value;

    private Score(int value) {
        this.value = value;
    }

    public static Score from(int value) {
        if (value <= MAX_CACHE_SCORE) {
            return POOL.get(value);
        }
        return new Score(value);
    }

    public Score add(Score other) {
        return new Score(this.value + other.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Score score)) {
            return false;
        }
        return value == score.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
