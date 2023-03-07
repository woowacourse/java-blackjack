package domain.card;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public final class Score {

    private final int score;

    private static final Map<Integer, Score> CACHE = new ConcurrentHashMap<>();

    private Score(final int score) {
        this.score = score;
    }

    public static Score create(final int score) {
        return CACHE.computeIfAbsent(score, mapping -> new Score(score));
    }

    @Override
    public boolean equals(final Object target) {
        if (this == target) {
            return true;
        }
        if (!(target instanceof Score)) {
            return false;
        }
        Score targetScore = (Score) target;
        return score == targetScore.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }

    public int getScore() {
        return score;
    }
}
