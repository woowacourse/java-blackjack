package blackjack;

public enum ResultStatus {
    WIN,
    LOSE,
    DRAW;

    public ResultStatus reverse() {
        return switch (this) {
            case LOSE -> WIN;
            case DRAW -> DRAW;
            case WIN -> LOSE;
        };
    }
}
