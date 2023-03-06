package blackjack.domain;

public enum Result {
    WIN,
    DRAW,
    LOSE;

    public static Result from(int score, int compareScore) {
        if (score > compareScore) {
            return WIN;
        }
        if (score < compareScore) {
            return LOSE;
        }
        return DRAW;
    }
}
