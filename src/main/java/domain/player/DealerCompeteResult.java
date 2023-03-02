package domain.player;

public enum DealerCompeteResult {
    WIN,
    LOSE,
    DRAW;

    public DealerCompeteResult reverse() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return this;
    }
}