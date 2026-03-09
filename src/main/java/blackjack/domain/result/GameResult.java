package blackjack.domain.result;

public enum GameResult {

    WIN,
    DRAW,
    LOSE;

    public GameResult reverse() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }
}
