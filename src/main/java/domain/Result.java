package domain;

import java.util.function.IntUnaryOperator;

public enum Result {
    WIN(bettingAmount -> bettingAmount),
    LOSE(bettingAmount -> bettingAmount * -1),
    DRAW(bettingAmount -> 0),
    BLACKJACK(bettingAmount -> (int) (bettingAmount * 1.5));

    private final IntUnaryOperator operator;

    Result(IntUnaryOperator operator) {
        this.operator = operator;
    }

    public int calculateWinningAmount(int bettingAmountValue) {
        return operator.applyAsInt(bettingAmountValue);
    }
}
