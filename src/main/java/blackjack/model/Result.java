package blackjack.model;

public enum Result {
    WIN,
    DRAW,
    LOSE;

    public Result opposite() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }
}
