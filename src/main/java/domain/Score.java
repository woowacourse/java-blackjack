package domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Score {

    private static final Map<Integer, Score> CACHE = new HashMap<>();
    public static final Score INITIAL_SCORE = Score.from(0);
    public static final Score MAX_SCORE = Score.from(21);
    private static final Score ACE_DECREASE = Score.from(10);
    private static final int NONE_ACE_COUNT = 0;

    private final int value;

    private Score(int value) {
        this.value = value;
    }

    public static Score from(int value) {
        return CACHE.computeIfAbsent(value, k -> new Score(value));
    }

    public Score add(Score other) {
        return Score.from(this.getValue() + other.getValue());
    }

    public boolean isLessThanOrEqual(Score compareTarget) {
        return this.getValue() <= compareTarget.getValue();
    }

    public Score decreaseScoreByAce(Score limit, int aceCount) {
        Score score = this;
        while (isScoreDecreasableByAce(limit, aceCount)) {
            score = score.minus(ACE_DECREASE);
            aceCount--;
        }

        return score;
    }

    private boolean isScoreDecreasableByAce(Score limit, int aceCount) {
        return !isMaxScore() && this.isGreaterThan(limit) && NONE_ACE_COUNT < aceCount;
    }

    private Score minus(Score other) {
        return Score.from(this.getValue() - other.getValue());
    }

    public boolean isMaxScore() {
        return this.equals(MAX_SCORE);
    }

    public boolean isBust() {
        return this.isGreaterThan(MAX_SCORE);
    }

    public boolean isGreaterThan(Score compareTarget) {
        return this.getValue() > compareTarget.getValue();
    }

    public int getValue() {
        return value;
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

}
