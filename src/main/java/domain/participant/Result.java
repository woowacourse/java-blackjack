package domain.participant;

public enum Result {
    WIN,
    LOSE,
    DRAW;

    public Result reverseResult() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return this;
    }
}
