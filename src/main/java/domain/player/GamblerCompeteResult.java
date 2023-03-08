package domain.player;

public enum GamblerCompeteResult {
    WIN,
    LOSE,
    DRAW,
    ;

    public GamblerCompeteResult reverse() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return this;
    }
}
