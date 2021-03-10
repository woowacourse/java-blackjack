package blackjack.domain;

import java.util.Objects;

public class Score implements Comparable<Score> {

    private static final int BLACKJACK = 21;
    private static final int GAP_BETWEEN_ACE_MAX_AND_MIN = 10;

    private final int score;

    public Score(int score) {
        this.score = score;
    }

    public boolean isNotBust() {
        return !isBust();
    }

    public boolean isBust() {
        return score > BLACKJACK;
    }

    public boolean isBlackJack() {
        return score == BLACKJACK;
    }

    public boolean isLessThanEleven() {
        return score + GAP_BETWEEN_ACE_MAX_AND_MIN <= BLACKJACK;
    }

    public Score plus() {
        return new Score(score + GAP_BETWEEN_ACE_MAX_AND_MIN);
    }

    public boolean isLowerThan(int threshold) {
        return score <= threshold;
    }

    public int toInt() {
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

    @Override
    public int compareTo(Score that) {
        return Integer.compare(this.score, that.score);
    }
}
