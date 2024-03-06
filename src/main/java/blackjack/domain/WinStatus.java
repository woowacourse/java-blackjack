package blackjack.domain;

public enum WinStatus {
    LOSE, DRAW, WIN;

    public static WinStatus of(final Score dealerScore, final Score playerScore) {
        // TODO : 라인수 줄이기
        final BlackjackStatus playerStatus = BlackjackStatus.from(playerScore);
        final BlackjackStatus dealerStatus = BlackjackStatus.from(dealerScore);

        if (playerStatus.isDead()) {
            return LOSE;
        }
        if (playerScore.equals(dealerScore)) {
            return DRAW;
        }
        if (playerStatus.isBlackjack() || dealerStatus.isDead() || playerScore.isBiggerThan(dealerScore)) {
            return WIN;
        }
        return LOSE;
    }
}