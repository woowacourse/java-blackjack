package blackjack.domain;

import java.util.Objects;

public class Score {
    private static final int BUST_SCORE_BOUNDARY = 21;

    private final int score;

    public Score(int value) {
        this.score = value;
    }

    public boolean isWinTo(Score other) {
        if (other.isBust()) {
            return true;
        }
        if (this.isBust()) {
            return false;
        }
        return this.score > other.score;
    }

    private boolean isBust() {
        return score > BUST_SCORE_BOUNDARY;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Score)) {
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
