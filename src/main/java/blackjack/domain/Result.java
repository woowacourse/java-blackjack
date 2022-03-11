package blackjack.domain;

public enum Result {

    WIN("승"),
    DRAW("무"),
    LOSE("패");

    public static final int MAX_SCORE = 21;

    private final String result;

    Result(String result) {
        this.result = result;
    }

    public static Result findResult(int myScore, int otherScore) {
        if (myScore > MAX_SCORE && otherScore > MAX_SCORE) {
            return DRAW;
        }
        if (myScore > MAX_SCORE) {
            return LOSE;
        }
        if (otherScore > MAX_SCORE) {
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

    public String getResult() {
        return result;
    }
}
