package blackjack.player;

import java.util.Objects;

public class Score {

    private static final int BLACKJACK = 21;
    private static final int ADDITIONAL_ACE = 10;

    private final int score;

    public Score(int score) {
        this.score = score;
    }

    public Score add(int score) {
        return add(new Score(score));
    }

    public Score add(Score other) {
        return new Score(this.score + other.score);
    }

    public boolean isBust() {
        return score > BLACKJACK;
    }

    public boolean isNotBust() {
        return !isBust();
    }

    public boolean isLargerThan(Score other) {
        return this.score > other.score;
    }

    public boolean isSmallerThanOrEqualTo(Score other) {
        return !isLargerThan(other);
    }

    public Score changeToLargeAceScore() {
        Score largeAceScore = add(ADDITIONAL_ACE);
        if (largeAceScore.isBust()) {
            return this;
        }
        return largeAceScore;
    }

    public boolean isBlackJack() {
        return score == BLACKJACK;
    }

    public int getScore() {
        return score;
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
