package blackjack.domain;

public enum ScoreCompareResult {
    PLAYER_WIN,
    DEALER_WIN,
    PLAYER_LOSS,
    DEALER_LOSS,
    PUSH;

    public boolean isPlayerWin() {
        return this == PLAYER_WIN || this == DEALER_LOSS;
    }

    public boolean isPlayerLoss() {
        return this == PLAYER_LOSS || this == DEALER_WIN;
    }
}
