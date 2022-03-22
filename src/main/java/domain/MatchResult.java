package domain;

import java.util.function.BiFunction;

public enum MatchResult {
    WIN((money, isBlackjack) -> isBlackjack ? money * 1.5 : money),
    DRAW((money, isBlackjack) -> (double) 0),
    LOSE((money, isBlackjack) -> (double) (money * -1)),
    ;

    private final BiFunction<Integer, Boolean, Double> revenueCalculator;

    MatchResult(BiFunction<Integer, Boolean, Double> revenueCalculator) {
        this.revenueCalculator = revenueCalculator;
    }

    public double calculateRevenue(int money, boolean isBlackjack) {
        return this.revenueCalculator.apply(money, isBlackjack);
    }
}
