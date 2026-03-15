package domain;

public record Score(int value) {
    private static final int BLACKJACK_SCORE = 21;

    public boolean isBust() {
        return value > BLACKJACK_SCORE;
    }

    public boolean isBlackjackScore() {
        return value == BLACKJACK_SCORE;
    }
}
