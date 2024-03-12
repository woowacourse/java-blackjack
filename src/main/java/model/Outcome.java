package model;

public enum Outcome {
    WIN,
    LOSE,
    DRAW,
    ;

    public Outcome reverse() {
        if (this == Outcome.WIN) {
            return Outcome.LOSE;
        }
        if (this == Outcome.LOSE) {
            return Outcome.WIN;
        }
        return this;
    }
}
