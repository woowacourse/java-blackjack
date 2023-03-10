package blackjack.domain;

public enum ScoreState {

    BLACKJACK(21),
    BUST(22),
    STAY(17),
    HIT(16);

    private final int score;

    ScoreState(int score) {
        this.score = score;
    }

    public static ScoreState of(int score, int cardCount) {
        if (score == BLACKJACK.score && cardCount == 2) {
            return BLACKJACK;
        }
        if (score >= BUST.score) {
            return BUST;
        }
        if (score >= STAY.score) {
            return STAY;
        }
        return HIT;
    }

    public boolean isBlackjack() {
        return this == BLACKJACK;
    }

    public boolean isBust() {
        return this == BUST;
    }

    public boolean isHit() {
        return this == HIT;
    }

    public boolean isStay() {
        return this == STAY;
    }
}
