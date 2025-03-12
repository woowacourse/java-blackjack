package model.score;

public enum MatchResult {

    WIN,
    LOSE,
    DRAW;

    public MatchResult reverse() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return this;
    }
}
