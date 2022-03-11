package blackjack.domain;

public enum Result {

    WIN("승"),
    DRAW("무"),
    LOSE("패");

    public static final int BLACK_JACK_SCORE = 21;

    private final String result;

    Result(String result) {
        this.result = result;
    }

    public static Result findResult(int myScore, int otherScore) {
        if (myScore > BLACK_JACK_SCORE && otherScore > BLACK_JACK_SCORE) {
            return DRAW;
        }
        if (myScore > BLACK_JACK_SCORE) {
            return LOSE;
        }
        if (otherScore > BLACK_JACK_SCORE) {
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
