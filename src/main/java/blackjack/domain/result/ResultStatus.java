package blackjack.domain.result;

public enum ResultStatus {

    BLACKJACK,
    WIN,
    LOSE,
    PUSH;

    public ResultStatus makeOppositeResult() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return PUSH;
    }
}
