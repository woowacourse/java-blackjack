package domain.profit;

import java.util.function.Function;

public enum Profit {
    WIN_BLACKJACK(betAmount -> betAmount * 1.5),
    WIN(betAmount -> betAmount * 1.0),
    LOSE(betAmount -> betAmount * -1.0),
    DRAW(betAmount -> betAmount * 0.0);

    private final Function<Long, Double> profitFunction;

    Profit(final Function<Long, Double> profitFunction) {
        this.profitFunction = profitFunction;
    }

    public long calculate(final long profit) {
        final Double result = profitFunction.apply(profit);
        return result.longValue();
    }
}
