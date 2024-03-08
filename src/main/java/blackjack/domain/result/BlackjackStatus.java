package blackjack.domain.result;

public enum BlackjackStatus {
    DEAD,
    ALIVE,
    BLACKJACK;

    public static final int BLACKJACK_NUMBER = 21;
    private static final Score BLACKJACK_SCORE = new Score(BLACKJACK_NUMBER);

    public static BlackjackStatus from(final Score score) {
        if (score.isBiggerThan(BLACKJACK_SCORE)) {
            return DEAD;
        }
        if (score.equals(BLACKJACK_SCORE)) {
            return BLACKJACK;
        }
        return ALIVE;
    }

    public boolean isDead() {
        return this == DEAD;
    }

    public boolean isBlackjack() {
        return this == BLACKJACK;
    }
}
