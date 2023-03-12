package domain.score;

import domain.player.Status;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public final class Score {

    private static final int MAX_SCORE = 21;
    private static final Map<Integer, Score> cache = new ConcurrentHashMap<>();

    private final int value;

    private Score(final int value) {
        this.value = value;
    }

    public static Score from(final int value) {
        if (!cache.containsKey(value)) {
            cache.put(value, new Score(value));
        }
        return cache.get(value);
    }

    public Status compareScore(final Score score) {
        if (this.value > score.value) {
            return Status.WIN;
        }
        if (this.value == score.value) {
            return Status.DRAW;
        }
        return Status.LOSE;
    }

    public boolean isSmallerOrEqual(final int number) {
        return value <= number;
    }

    public boolean isBlackjackCandidate() {
        return value == MAX_SCORE;
    }

    public int getValue() {
        return value;
    }

    public boolean isBusted() {
        return value > MAX_SCORE;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return value == score.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
