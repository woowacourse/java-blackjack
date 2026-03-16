package domain.result;

public record Score(int score) {
    private static final int BLACKJACK_SCORE = 21;

    public int getGameScore() {
        return score;
    }

    public boolean isBust() {
        return score > BLACKJACK_SCORE;
    }

    public boolean isBlackjack() {
        return score == BLACKJACK_SCORE;
    }

    public boolean isLessThanOrEqual(int bound) {
        return score <= bound;
    }

    public boolean isGreaterThan(Score other) {
        return this.score > other.score;
    }

    public boolean isLessThan(Score other) {
        return this.score < other.score;
    }
}
