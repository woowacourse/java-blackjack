package blackjack.domain.card;

import java.util.Objects;

public class Score {
    public static final int BLACK_JACK = 21;
    public static final int MIN_BUST_SCORE = 22;
    public static final Score ZERO_SCORE = new Score(0);
    public static final Score ADDITIONAL_ACE_SCORE = new Score(10);

    private final int score;

    private Score(final int value) {
        this.score = value;
    }

    public static Score of(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("유효하지 않은 점수입니다.");
        }
        if (value == 0) {
            return ZERO_SCORE;
        }
        return new Score(value);
    }

    public Score addScore(Score score) {
        return of(this.score + score.score);
    }

    public boolean isTwentyOne() {
        return score == BLACK_JACK;
    }

    public boolean isBust() {
        return score > BLACK_JACK;
    }

    public boolean isBiggerThan(final Score target) {
        return score > target.score;
    }

    public boolean isLessThan(final Score target) {
        return score < target.score;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return this.score == score.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
