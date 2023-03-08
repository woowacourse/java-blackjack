package domain;

import java.util.Objects;

public class Score {
    private static final Score bonus = new Score(10);
    private static final Score max = new Score(21);

    private final int value;

    public Score(int score) {
        this.value = score;
    }

    public Score add(Score other) {
        return new Score(value + other.getValue());
    }

    public Score addScoreByAce() {
        Score sumScore = add(bonus);
        if (sumScore.value <= max.value) {
            return new Score(bonus.value + value);
        }
        return this;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
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
