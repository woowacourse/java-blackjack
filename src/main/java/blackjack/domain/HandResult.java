package blackjack.domain;

public enum HandResult {
    WIN,
    DRAW,
    LOSE;

    public HandResult getOpposite() {
        if (WIN.equals(this)) {
            return LOSE;
        }
        if (LOSE.equals(this)) {
            return WIN;
        }
        return DRAW;
    }
}
