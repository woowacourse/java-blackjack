package domain;

public enum PlayerResult {
    BLACK_JACK_WIN,
    WIN,
    LOSE,
    TIE;

    public PlayerResult reverse() {
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
