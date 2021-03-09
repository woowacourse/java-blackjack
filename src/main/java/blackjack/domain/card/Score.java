package blackjack.domain.card;

import java.util.Objects;

public class Score {
    private final static int BLACK_JACK = 21;
    public final static Score ZERO_SCORE = new Score(0);

    private final int value;

    public Score(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("유효하지 않은 점수입니다.");
        }

        this.value = value;
    }

    public boolean isBust() {
        return value > BLACK_JACK;
    }

    public boolean isBlackJack() {
        return value == BLACK_JACK;
    }

    public boolean isHigherScore(Score score){
        return this.value > score.value;
    }

    public boolean isLowerScore(Score score){
        return this.value < score.value;
    }

    public boolean isSameScore(Score score){
        return this.value == score.value;
    }

    public boolean isBelow(int score) {
        return value <= score;
    }

    public boolean isNotBustAndHigh(final Score target) {
        if (!this.isBust() && value > target.value) {
            return true;
        }

        return false;
    }

    public Score useAceAsEleven() {
        return new Score(value + 10);
    }

    public Score addScore(final Score score) {
        return new Score(score.value + this.value);
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
