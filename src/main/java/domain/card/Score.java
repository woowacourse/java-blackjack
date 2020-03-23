package domain.card;

import java.util.Objects;

public class Score {
    private static final int NONE_ACE_COUNT = 0;
    private static final String INVALID_PARAMETER_EXCEPTION_MESSAGE = "Invalid parameter exception. (score/aceCount can not negative";
    private final int BLACK_JACK = 21;
    private static final int ACE_WEIGHT = 10;

    private final int score;

    public Score(int score) {
        this(score, NONE_ACE_COUNT);
    }

    public Score(int score, int aceCount) {
        if (score<0 || aceCount < 0) {
            throw new IllegalArgumentException(INVALID_PARAMETER_EXCEPTION_MESSAGE);
        }
        this.score = addAceWeight(score, aceCount);
    }

    private int addAceWeight(int score, long aceCount) {
        while (aceCount > 0 && score + ACE_WEIGHT <= BLACK_JACK) {
            score += ACE_WEIGHT;
            aceCount--;
        }
        return score;
    }

    public boolean isBlackJackScore() {
        return score == BLACK_JACK;
    }

    public boolean isBust() {
        return score > BLACK_JACK;
    }

    public boolean canHit(int maxHitScore) {
        return this.score <= maxHitScore;
    }

    public boolean isMoreThan(Score score) {
        return this.score > score.getScore();
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return String.valueOf(score);
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
