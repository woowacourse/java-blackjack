package blackjack.model;

public class Score {
    public static final int BUST_BOUNDARY = 21;
    public static final int DEALER_HIT_BOUNDARY = 16;
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
        return score <= DEALER_HIT_BOUNDARY;
    }

    public boolean isPlayerHitScore() {
        return score <= BUST_BOUNDARY;
    }

    public boolean isBust() {
        return score > BUST_BOUNDARY;
    }

    public int getScore() {
        return score;
    }
}
