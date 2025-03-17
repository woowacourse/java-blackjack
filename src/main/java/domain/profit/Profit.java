package domain.profit;

import java.util.Arrays;
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

    public static Profit findByOutcome(final Outcome outcome) {
        return Arrays.stream(Profit.values())
                .filter(value -> value.name().equals(outcome.name()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 결과입니다." + outcome.name()));
    }

    public Double calculate(final Long profit) {
        return profitFunction.apply(profit);
    }
}
