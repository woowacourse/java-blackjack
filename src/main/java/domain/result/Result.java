package domain.result;

import java.util.function.Function;

public enum Result {
    BLACKJACK_WIN("승", betAmount -> (int) (betAmount * 1.5)),
    WIN("승", betAmount -> betAmount),
    DRAW("무", betAmount -> 0),
    LOSE("패", betAmount -> -betAmount);

    private final String korean;
    private final Function<Integer, Integer> getRevenue;

    Result(final String resultKorean, final Function<Integer, Integer> getRevenue) {
        this.korean = resultKorean;
        this.getRevenue = getRevenue;
    }

    public int revenueOf(int betAmount) {
        return getRevenue.apply(betAmount);
    }

    public Result getOpponentResult() {
        if (this == Result.WIN) {
            return Result.LOSE;
        }
        if (this == Result.LOSE) {
            return Result.WIN;
        }
        return Result.DRAW;
    }

    @Override
    public String toString() {
        return this.korean;
    }
}
