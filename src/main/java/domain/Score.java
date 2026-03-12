package domain;

public final class Score {
    private final int score;

    public Score() {
        this(0);
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
