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

    public boolean isWin() {
        return this == WIN;
    }

    public boolean isLose() {
        return this == LOSE;
    }
}
