package blackjack.domain.game;

public enum GameResult {
    WIN,
    LOSE,
    DRAW;

    public GameResult reverse() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return this;
    }
}
