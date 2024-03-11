package blackjack.domain;

public class Result {

    private static final Result WIN_RESULT = new Result(1, 0);
    private static final Result LOSS_RESULT = new Result(0, 1);

    private int winCount;
    private int loseCount;

    public Result() {
        this(0, 0);
    }

    private Result(int winCount, int loseCount) {
        this.winCount = winCount;
        this.loseCount = loseCount;
    }

    public static Result createWinResult() {
        return WIN_RESULT;
    }

    public static Result createLossResult() {
        return LOSS_RESULT;
    }

    public void updateOpponent(Result result) {
        this.winCount += result.loseCount;
        this.loseCount += result.winCount;
    }

    public int getWinCount() {
        return winCount;
    }

    public int getLoseCount() {
        return loseCount;
    }
}
