package blackjack.domain.status;

import blackjack.domain.Score;

public enum BlackjackStatus {
    DEAD,
    ALIVE,
    BLACKJACK;

    public static final int BLACKJACK_NUMBER = 21;
    private static final Score BLACKJACK_SCORE = new Score(BLACKJACK_NUMBER);

    public static BlackjackStatus from(final ParticipantScoreStatus playerScoreStatus) {
        if (playerScoreStatus.isScoreBiggerThan(BLACKJACK_SCORE)) {
            return DEAD;
        }
        if (playerScoreStatus.isBlackjack()) {
            return BLACKJACK;
        }
        return ALIVE;
    }

    public static BlackjackStatus of(final Score score, final int count) {
        if (score.isBiggerThan(BLACKJACK_SCORE)) {
            return DEAD;
        }
        if (score.equals(BLACKJACK_SCORE) && count == 2) {
            return BLACKJACK;
        }
        return ALIVE;
    }
}
