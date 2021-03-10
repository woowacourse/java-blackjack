package blackjack.domain.card;

public class Score {
    private static final int BLACKJACK = 21;
    private final int score;

    public Score(int score) {
        this.score = score;
    }

    public Score plusTenIfNotBust() {
        Score score = new Score(this.score + 10);
        if (score.isBust()) {
            return this;
        }
        return score;
    }

    protected boolean isBust() {
        return this.score > BLACKJACK;
    }

    protected boolean isBlackjack() {
        return this.score == BLACKJACK;
    }

    public int toInt() {
        return score;
    }

    public boolean isOver(int turnOverCount) {
        return this.score > turnOverCount;
    }
}
