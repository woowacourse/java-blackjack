package domain;

public final class Score {
    public static final Score ZERO = new Score(0);

    private final int score;

    public Score() {
        this(0);
    }

    public static Score zero() {
        return ZERO;
    }

    private Score(int score) {
        this.score = score;
    }

    public Score addScore(int number) {
        return new Score(score + number);
    }

    public Score subScore(int number) {
        return new Score(score - number);
    }

    public int getScore() {
        return score;
    }

    public boolean isBust() {
        return score > GameRule.BLACKJACK_SCORE;
    }

    public boolean isDealerDraw() {
        return score <= GameRule.DEALER_SCORE;
    }
}
