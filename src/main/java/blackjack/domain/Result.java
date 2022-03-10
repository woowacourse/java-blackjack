package blackjack.domain;

public enum Result {

    WIN,
    LOSE,
    DRAW;

    public static Result findResult(int myScore, int otherScore) {
        if (myScore > 21 && otherScore > 21) {
            return DRAW;
        }
        if (myScore > 21) {
            return LOSE;
        }
        if (otherScore > 21) {
            return WIN;
        }
        return compareScore(myScore, otherScore);
    }

    private static Result compareScore(int myScore, int otherScore) {
        if (myScore > otherScore) {
            return WIN;
        }
        if (myScore < otherScore) {
            return LOSE;
        }
        return DRAW;
    }
}
