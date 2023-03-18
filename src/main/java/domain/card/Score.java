package domain.card;

import java.util.Objects;

public class Score {
    private static final int MAX_SCORE = 21;
    private static final int DIFFERENCE_BETWEEN_ACE_ONE_AND_ELEVEN = 10;

    private final int score;

    public Score(int score) {
        this.score = score;
    }

    public Score add(Score other) {
        return new Score(this.score + other.score);
    }

    public boolean isLessThanOrEqual(Score other) {
        return isLessThan(other) || score == other.score;
    }

    public boolean isLessThan(Score other) {
        return score < other.score;
    }

    public boolean isOverMax() {
        return MAX_SCORE < score;
    }

    public boolean isMax() {
        return score == MAX_SCORE;
    }

    public Score calculateAceAsOne(int aceCount) {
        int newScore = score;
        while (0 < aceCount && MAX_SCORE < newScore) {
            newScore -= DIFFERENCE_BETWEEN_ACE_ONE_AND_ELEVEN;
            aceCount--;
        }
        return new Score(newScore);
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
