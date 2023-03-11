package domain.user;

import java.util.Objects;

public class Score {
    private static final Score max = new Score(21);
    private static final int DIFFERENCE_BETWEEN_ACE_ONE_AND_ELEVEN = 10;

    private final int score;

    public Score(int score) {
        this.score = score;
    }

    public Score add(Score other) {
        return new Score(this.score + other.getScore());
    }

    public boolean isLessThanOrEqual(Score other) {
        return isLessThan(other) || score == other.getScore();
    }

    public boolean isLessThan(Score other) {
        return score < other.getScore();
    }

    public boolean isOverMax() {
        return max.isLessThan(this);
    }

    public Score calculateAceAsOne(int aceCount) {
        Score newScore = new Score(this.score);
        while (aceCount > 0 && newScore.isOverMax()) {
            newScore = new Score(newScore.getScore() - DIFFERENCE_BETWEEN_ACE_ONE_AND_ELEVEN);
            aceCount--;
        }
        return newScore;
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
