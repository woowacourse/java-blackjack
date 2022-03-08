package blackjack.domain.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Score implements Comparable<Score> {
    private final int value;

    private Score(final int value) {
        this.value = value;
    }

    public static Score valueOf(final int value) {
        return ScoreCache.getCache(value);
    }

    public Score add(Score anotherScore) {
        int addedValue = this.value + anotherScore.value;
        return ScoreCache.getCache(addedValue);
    }

    public int toInt() {
        return value;
    }

    @Override
    public int compareTo(Score o) {
        return this.value - o.value;
    }

    @Override
    public String toString() {
        return "Score{" + "value=" + value + '}';
    }

    private static class ScoreCache {
        static Map<Integer, Score> cache = new HashMap<>();

        static Score getCache(final int value) {
            return Optional.ofNullable(cache.get(value))
                    .orElseGet(() -> createNewCache(value));
        }

        static Score createNewCache(final int value) {
            cache.put(value, new Score(value));
            return cache.get(value);
        }
    }
}
