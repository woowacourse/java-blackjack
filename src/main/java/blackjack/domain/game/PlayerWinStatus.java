package blackjack.domain.game;

public enum PlayerWinStatus {
    WIN,
    LOSE,
    PUSH;

    public PlayerWinStatus reverse() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return this;
    }
}
