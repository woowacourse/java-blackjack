package blackjack.domain.game;

public enum PlayerGameResult {
    WIN,
    LOSE,
    PUSH;

    public PlayerGameResult reverse() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return this;
    }
}
