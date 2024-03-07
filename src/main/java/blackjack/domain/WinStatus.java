package blackjack.domain;

public enum WinStatus {
    LOSE,
    DRAW,
    WIN;

    public static WinStatus of(final Score dealerScore, final Score playerScore) {
        if (BlackjackStatus.from(playerScore).isDead()) {
            return LOSE;
        }
        if (BlackjackStatus.from(dealerScore).isDead() || playerScore.isBiggerThan(dealerScore)) {
            return WIN;
        }
        if (dealerScore.isBiggerThan(playerScore)) {
            return LOSE;
        }
        return DRAW;
    }

    public WinStatus opposite() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }
}