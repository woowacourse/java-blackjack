package domain.result.score;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Score {
    private final Integer value;

    public Score(int value) {
        this.value = value;
    }

    public static Score of(int value) {
        return new Score(value);
    }

    public Score plus(Score operand) {
        return Score.of(this.value + operand.value);
    }

    public boolean isBiggerThan(Score compared) {
        return this.value > compared.value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return Objects.equals(value, score.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
