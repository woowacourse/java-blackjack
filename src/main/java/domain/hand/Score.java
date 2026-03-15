package domain.hand;

public record Score(int value) {
    private static final int BUST_LIMIT_SCORE = 21;

    public boolean isBust() {
        return value > BUST_LIMIT_SCORE;
    }
}
