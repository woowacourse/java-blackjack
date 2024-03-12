package blackjack.model.blackjackgame;

import java.util.function.Function;

public enum PlayerProfitCalculator {
    BLACKJACK(value -> (value * 1.5)),
    WIN(value -> value),
    LOSE(value -> -value),
    PUSH(value -> (double) 0);

    private final Function<Double, Double> function;

    PlayerProfitCalculator(Function<Double, Double> function) {
        this.function = function;
    }

    public Double calculate(double value) {
        return function.apply(value);
    }
}
