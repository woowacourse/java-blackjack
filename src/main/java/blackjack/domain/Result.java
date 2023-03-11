package blackjack.domain;

public enum Result {
    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    BLACKJACK("블랙잭");

    private final String result;

    Result(String result) {
        this.result = result;
    }

    public static Result getLeftResult(Score leftScore, Score rightScore, Score standard) {
        if (rightScore.isGreaterThan(standard)) {
            return getLeftResultWhenRightScoreOverStandard(leftScore, standard);
        }
        if (leftScore.isLessThan(standard)) {
            return getLeftResultWhenRightScoreLessThanStandard(leftScore, rightScore);
        }
        return LOSE;
    }

    private static Result getLeftResultWhenRightScoreOverStandard(Score score, Score standard) {
        if (score.isLessThan(standard)) {
            return WIN;
        }
        return DRAW;
    }

    private static Result getLeftResultWhenRightScoreLessThanStandard(Score leftScore, Score rightScore) {
        if (leftScore.isGreaterThan(rightScore)) {
            return WIN;
        }
        if (leftScore.isLessThan(rightScore)) {
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
