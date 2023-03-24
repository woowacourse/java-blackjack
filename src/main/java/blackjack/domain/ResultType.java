package blackjack.domain;

import java.util.function.Function;

public enum ResultType {
    WIN(bettingAmount -> bettingAmount),
    LOSE(bettingAmount -> -bettingAmount),
    DRAW(bettingAmount -> 0),
    BLACKJACK(bettingAmount -> (int) (bettingAmount * 1.5));
    private final Function<Integer, Integer> expression;

    ResultType(Function<Integer, Integer> expression) {
        this.expression = expression;
    }

    public Integer calculateProfit(int bettingAmount) {
        return expression.apply(bettingAmount);
    }
}
