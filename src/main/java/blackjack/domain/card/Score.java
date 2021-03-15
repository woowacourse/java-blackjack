package blackjack.domain.card;

import java.util.Objects;

public class Score {
    private static final Score BLACKJACK = new Score(21);
    private static final Score TEN = new Score(10);
    private final int score;

    public Score(int score) {
        this.score = score;
    }

    public Score plusTenIfNotBust() {
        Score score = new Score(TEN.score + this.score);
        if (score.isBust()) {
            return this;
        }
        return score;
    }

    public int toInt() {
        return score;
    }

    public boolean isBust() {
        return BLACKJACK.score < this.score;
    }

    protected boolean isBlackjack() {
        return BLACKJACK.score == score;
    }

    public boolean lessThan(Score other) {
        return this.score < other.score;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Score){
            return ((Score) o).score == this.score;
        }

        return false;
    }


    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
