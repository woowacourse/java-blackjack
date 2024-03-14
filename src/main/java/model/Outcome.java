package model;

import java.util.function.Function;

public enum Outcome {
    WIN((bettingAmount) -> bettingAmount * 1.0),
    LOSE((bettingAmount) -> bettingAmount * -1.0),
    DRAW((bettingAmount) -> 0.0),
    BLACKJACK((bettingAmount) -> bettingAmount * 1.5),
    ;

    private final Function<Double, Double> function;

    Outcome(Function<Double, Double> function) {
        this.function = function;
    }

    public double calculateProfit(double BettingAmount) {
        return function.apply(BettingAmount);
    }

    public Outcome reverse() {
        if (this == Outcome.WIN) {
            return Outcome.LOSE;
        }
        if (this == Outcome.LOSE) {
            return Outcome.WIN;
        }
        return this;
    }
}
