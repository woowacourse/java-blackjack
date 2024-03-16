package blackjack.vo;

import java.util.Objects;

public class Score {
    private static final int WINNING_SCORE = 21;
    private static final int EXTRA_SCORE = 10;

    private final int score;

    public Score() {
        this(0);
    }

    public Score(int score) {
        validate(score);
        this.score = score;
    }

    public static Score withExtraScore(int calculatedScore) {
        return new Score(calculatedScore + EXTRA_SCORE);
    }

    public boolean isBust() {
        return score > WINNING_SCORE;
    }

    public boolean lessThanWinningScoreWithExtraScore() {
        return score + EXTRA_SCORE <= WINNING_SCORE;
    }

    public boolean isBlackJack() {
        return score == WINNING_SCORE;
    }

    public int value() {
        return score;
    }

    private void validate(int score) {
        if (score < 0) {
            throw new IllegalArgumentException("점수는 음수일 수 없습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Score score1 = (Score) o;
        return score == score1.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
