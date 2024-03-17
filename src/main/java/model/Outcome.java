package model;

import java.util.function.Function;

public enum Outcome {
    WIN((bettingAmount) -> bettingAmount.toInt() * 1.0),
    LOSE((bettingAmount) -> bettingAmount.toInt() * -1.0),
    DRAW((bettingAmount) -> 0.0),
    BLACKJACK((bettingAmount) -> bettingAmount.toInt() * 1.5),
    ;

    private final Function<BettingMoney, Double> function;

    Outcome(Function<BettingMoney, Double> function) {
        this.function = function;
    }

    public double calculateProfit(BettingMoney BettingMoney) {
        return function.apply(BettingMoney);
    }
}
