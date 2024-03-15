package domain;

public enum Result {
    WIN,
    LOSE,
    TIE;

    public Result reverse() {
        if (this==WIN) {
            return LOSE;
        }
        if (this==LOSE) {
            return WIN;
        }
        return TIE;
    }

    public boolean won() {
        return this == WIN;
    }

    public boolean lost() {
        return this == LOSE;
    }
}
