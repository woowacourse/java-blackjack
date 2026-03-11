package domain;

public enum MatchResult {
    WIN,
    DRAW,
    LOSE,
    ;

    public MatchResult opposite() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }
}
