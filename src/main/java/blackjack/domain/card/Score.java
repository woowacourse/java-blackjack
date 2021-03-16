package blackjack.domain.card;

import java.util.Objects;

public class Score {
    private static final Score BLACKJACK = Score.Of(21);
    private static final Score TEN = Score.Of(10);

    private final int score;

    private Score(int score) {
        this.score = score;
    }

    public static Score Of(int score) {
        if (BLACKJACK.equals(score)) {
            return BLACKJACK;
        }
        
        if (TEN.equals(score)) {
            return TEN;
        }
        return new Score(score);
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
        return BLACKJACK.lessThan(score);
    }

    private boolean lessThan(int score) {
        return this.score == score;
    }

    protected boolean isBlackjack() {
        return BLACKJACK.equals(Score.Of(score));
    }

    public boolean lessThan(Score other) {
        return this.score < other.score;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Score) {
            return ((Score) o).score == this.score;
        }

        return false;
    }


    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
