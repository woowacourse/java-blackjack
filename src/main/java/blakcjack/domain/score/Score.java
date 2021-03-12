package blakcjack.domain.score;

import blakcjack.exception.CacheMissException;
import blakcjack.exception.NegativeNumericException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Score {
    public static final int MIN_CACHE_VALUE = 0;
    public static final int MAX_CACHE_VALUE = 31;

    private static final List<Score> cache;

    static {
        cache = IntStream.rangeClosed(MIN_CACHE_VALUE, MAX_CACHE_VALUE)
                .mapToObj(Score::new)
                .collect(Collectors.toList());
    }

    private final int value;

    private Score(final int value) {
        validate(value);
        this.value = value;
    }

    public static Score from(final int value) {
        if (includeInCache(value)) {
            return Optional.ofNullable(cache.get(value))
                    .orElseThrow(() -> new CacheMissException(value));
        }
        return new Score(value);
    }

    private static boolean includeInCache(final int value) {
        return MIN_CACHE_VALUE <= value && value <= MAX_CACHE_VALUE;
    }

    private void validate(final int value) {
        if (value < 0) {
            throw new NegativeNumericException(value);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Score score = (Score) o;
        return value == score.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
