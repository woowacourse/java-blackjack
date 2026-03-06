package domain;

public final class Score {
    private static final int BLACKJACK_SCORE = 21;
    private static final int DEALER_SCORE = 16;

    private int score;

    public Score() {
        this.score = 0;
    }

    public void addScore(int number) {
        score += (number % 13);
    }

    public void subScore(int number) {
        score -= number;
    }

    public int getScore() {
        return score;
    }

    public boolean isBust() {
        return score > BLACKJACK_SCORE;
    }

    public boolean isBlackjack() {
        return score == BLACKJACK_SCORE;
    }

    public boolean isDealerDraw() {
        return score <= DEALER_SCORE;
    }
}
