package blackjack.model;

import java.util.Objects;

public class Score {
    public static final int MAX_SCORE = 21;
    public static final int DEALER_DRAW_THRESHOLD = 16;
    private final int score;

    public Score(int score) {
        this.score = score;
    }

    public boolean isSame(Score otherScore) {
        return score == otherScore.score;
    }

    public boolean isLess(Score otherScore) {
        return score < otherScore.score;
    }

    public boolean isDealerHitScore() {
        return score <= DEALER_DRAW_THRESHOLD;
    }

    public boolean isPlayerHitScore() {
        return score <= MAX_SCORE;
    }

    public boolean isMaxScore() {
        return score == MAX_SCORE;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(score);
    }
}
