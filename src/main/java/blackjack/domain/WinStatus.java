package blackjack.domain;

public enum WinStatus {
    LOSE, DRAW, WIN;

    public static WinStatus of(final Score dealerScore, final Score playerScore) {
        if (dealerScore.isBiggerThan(playerScore)) {
            return LOSE;
        }
        if (playerScore.isBiggerThan(dealerScore)) {
            return WIN;
        }
        return DRAW;
    }
}
