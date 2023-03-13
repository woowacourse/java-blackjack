package blackjack.domain.vo;

import blackjack.constants.ErrorCode;
import blackjack.domain.vo.exception.InvalidScoreException;
import java.util.Objects;

public class Score {

    private final int value;

    private Score(int value) {
        validate(value);
        this.value = value;
    }

    private void validate(int value) {
        if (value < 0) {
            throw new InvalidScoreException(ErrorCode.INVALID_SCORE);
        }
    }

    public static Score of(int value) {
        return new Score(value);
    }

    public Score add(Score other) {
        return new Score(this.value + other.value);
    }

    public boolean isGreaterOrEqualsTo(Score other) {
        return this.value >= other.value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
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
