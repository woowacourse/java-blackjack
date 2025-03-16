package domain;

import java.util.Objects;

public class Score implements Comparable<Score> {
    private static final int HIT_LIMIT = 21;
    private static final int DEALER_DRAW_THRESHOLD = 16;

    private final int value;

    public Score(int value) {
        this.value = value;
    }

    public boolean isBust() {
        return value > HIT_LIMIT;
    }

    public boolean isHit() {
        return value == HIT_LIMIT;
    }

    public boolean isDealerHasToDraw() {
        return value <= DEALER_DRAW_THRESHOLD;
    }

    public int value() {
        return value;
    }

    @Override
    public int compareTo(Score other) {
        return Integer.compare(value, other.value);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Score score = (Score) object;
        return value == score.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
