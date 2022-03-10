package blackjack.domain;

public enum Result {

    WIN,
    LOSE,
    DRAW;

    public static Result findResult(int myScore, int otherScore) {
        if (myScore > otherScore) {
            return WIN;
        }
        if (myScore < otherScore) {
            return LOSE;
        }
        return DRAW;
    }
}
