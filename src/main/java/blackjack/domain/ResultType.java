package blackjack.domain;

public enum ResultType {
    WIN,
    TIE,
    LOSE;

    public ResultType getOppositeResult() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == TIE) {
            return TIE;
        }
        return WIN;
    }
}
