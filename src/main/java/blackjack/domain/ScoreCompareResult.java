package blackjack.domain;

public enum ScoreCompareResult {
    PLAYER_WIN,
    PLAYER_LOSS,
    PUSH;

    public boolean isPlayerWin() {
        return this == PLAYER_WIN;
    }

    public boolean isPlayerLoss() {
        return this == PLAYER_LOSS;
    }
}
