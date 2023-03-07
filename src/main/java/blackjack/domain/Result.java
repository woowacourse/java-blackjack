package blackjack.domain;

public enum Result {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String result;

    Result(String result) {
        this.result = result;
    }

    public static Result getLeftResult(int leftScore, int rightScore, int standard) {
        if (rightScore > standard) {
            return getLeftResultWhenRightScoreOverStandard(leftScore, standard);
        }
        if (leftScore <= standard) {
            return getLeftResultWhenRightScoreLessThanStandard(leftScore, rightScore);
        }
        return LOSE;
    }

    private static Result getLeftResultWhenRightScoreOverStandard(int score, int standard) {
        if (score <= standard) {
            return WIN;
        }
        return DRAW;
    }

    private static Result getLeftResultWhenRightScoreLessThanStandard(int leftScore, int rightScore) {
        if (leftScore > rightScore) {
            return WIN;
        }
        if (leftScore < rightScore) {
            return LOSE;
        }
        return DRAW;
    }

    public static Result getOpponentResult(Result result) {
        if (result == WIN) {
            return LOSE;
        }
        if (result == DRAW) {
            return DRAW;
        }
        return WIN;
    }

    public String getResult() {
        return result;
    }
}
