package domain;

public final class Score {
    private static final int BLACKJACK_SCORE = 21;

    private final int score;

    public Score(int score) {
        this.score = score;
    }

    public int getGameScore() {
        return score;
    }

    public boolean isBust() {
        return score > BLACKJACK_SCORE;
    }

    public boolean isBlackjack() {
        return score == BLACKJACK_SCORE;
    }
}