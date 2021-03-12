package blackjack.domain.card;

import java.util.Objects;

public class Score {
    private static final int BLACKJACK = 21;
    public static final int TEN = 10;
    private final int score;

    public Score(int score) {
        this.score = score;
    }

    public Score plusTenIfNotBust() {
        Score score = new Score(this.score + TEN);
        if (score.isBust()) {
            return this;
        }
        return score;
    }

    public boolean isBust() {
        return this.score > BLACKJACK;
    }

    protected boolean isBlackjack() {
        return this.score == BLACKJACK;
    }

    public int toInt() {
        return score;
    }

    public boolean isBelow(int turnOverCount) {
        return this.score <= turnOverCount;
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
