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

    public Score(int value) {
        this.value = value;
    }

    public static Score of(int value) {
        if (cache.containsKey(value)) {
            return cache.get(value);
        }
        return new Score(value);
    }

    public Score plusTenIfNotBusted() {
        if (sum(aceAdditionalValue).isBiggerThan(max)) {
            return this;
        }
        return sum(aceAdditionalValue);
    }

    public Score sum(Score other) {
        return new Score(value + other.value);
    }

    public boolean isMax() {
        return this == max;
    }

    public boolean isOverThanMax() {
        return value > max.value;
    }

    public boolean isBiggerThan(Score other) {
        return value > other.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Score score = (Score) o;
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
