package blackjack.domain;

public enum Result {

    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String result;

    Result(String result) {
        this.result = result;
    }

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

    public String getResult() {
        return result;
    }
}
