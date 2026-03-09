package domain;

import java.util.Objects;

public class Score {
    private final int value;

    public Score(int value) {
        validate(value);
        this.value = value;
    }

    private void validate(int value) {
        if (value > Constant.SCORE_MAX_SIZE || value < Constant.SCORE_MIN_SIZE) {
            throw new IllegalArgumentException(ExceptionMessage.SCORE_OUT_OF_RANGE.getMessage());
        }
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
