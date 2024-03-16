package domain.player;

public enum Result {
    WIN,
    LOSE,
    TIE;

    public static Result reverse(final Result result) {
        if (result == WIN) {
            return LOSE;
        }
        if (result == LOSE) {
            return WIN;
        }
        return result;
    }
}
