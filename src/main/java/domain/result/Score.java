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
}
