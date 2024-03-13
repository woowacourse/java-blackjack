package blackjack.model.result;

public enum ResultCommand {
    WIN,
    LOSE,
    DRAW,
    ;

    public ResultCommand findOpposite() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }
}
