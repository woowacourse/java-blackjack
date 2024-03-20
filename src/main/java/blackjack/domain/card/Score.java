package blackjack.domain.card;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Score {
    private static final int MIN_CACHE_SCORE = 0;
    private static final int MAX_CACHE_SCORE = 30;
    private static final int BLACKJACK_SCORE = 21;
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

    public static Score getZero() {
        return POOL.get(MIN_CACHE_SCORE);
    }

    public static Score from(int value) {
        if (value <= MAX_CACHE_SCORE) {
            return POOL.get(value);
        }
        return new Score(value);
    }

    public Score add(Score other) {
        int findValue = this.value + other.value;
        if (POOL.containsKey(findValue)) {
            return POOL.get(findValue);
        }
        return new Score(findValue);
    }

    public boolean isNotBurst() {
        return value <= BLACKJACK_SCORE;
    }

    public boolean isBlackJackScore() {
        return value == BLACKJACK_SCORE;
    }

    public boolean isBiggerThan(Score other) {
        return value > other.value;
    }

    public int getValue() {
        return value;
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
