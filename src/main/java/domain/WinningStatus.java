package domain;

public enum WinningStatus {
    WIN, LOSE, DRAW;

    public WinningStatus reverse() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }
}
