package blackjack.domain.game;

import java.util.List;

public class Result {

    private static final Result WIN_RESULT = new Result(1, 0);
    private static final Result LOSS_RESULT = new Result(0, 1);

    private final int winCount;
    private final int loseCount;

    public Result() {
        this(0, 0);
    }

    private Result(int winCount, int loseCount) {
        this.winCount = winCount;
        this.loseCount = loseCount;
    }

    public static Result create(List<Result> results) {
        return results.stream()
                .reduce(new Result(), Result::add);
    }

    public static Result createWinResult() {
        return WIN_RESULT;
    }

    public static Result createLossResult() {
        return LOSS_RESULT;
    }

    private Result add(Result other) {
        return new Result(this.winCount + other.loseCount, this.loseCount + other.winCount);
    }

    public int getWinCount() {
        return winCount;
    }

    public int getLoseCount() {
        return loseCount;
    }
}
