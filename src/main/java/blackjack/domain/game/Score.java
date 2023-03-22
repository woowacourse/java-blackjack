package blackjack.domain.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Score {

    private static final Score min = new Score(0);
    private static final Score max = new Score(21);
    private static final Score aceAdditionalValue = new Score(10);
    private static final Map<Integer, Score> cache = new HashMap<>();

    private final int value;

    public Score(final int value) {
        this.value = value;
    }

    public static Score of(final int value) {
        if (cache.containsKey(value)) {
            return cache.get(value);
        }
        final Score newScore = new Score(value);
        cache.put(value, newScore);
        return newScore;
    }

    public Score plusTenIfNotBusted() {
        if (sum(aceAdditionalValue).isBiggerThan(max)) {
            return this;
        }
        return sum(aceAdditionalValue);
    }

    public Score sum(final Score other) {
        return new Score(value + other.value);
    }

    public boolean isMax() {
        return equals(max);
    }

    public boolean isOverThanMax() {
        return value > max.value;
    }

    public boolean isBiggerThan(final Score other) {
        return value > other.value;
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

    public int getValue() {
        return value;
    }

}
