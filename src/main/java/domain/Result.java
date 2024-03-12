package domain;

public enum Result {
    WIN,
    LOSE,
    TIE;

    public boolean won() {
        return this == WIN;
    }

    public boolean lost() {
        return this == LOSE;
    }
}
