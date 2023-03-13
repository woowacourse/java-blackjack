package domain;

import java.util.function.IntUnaryOperator;

public enum Result {
    WIN(bettingMoney -> bettingMoney),
    LOSE(bettingMoney -> bettingMoney * -1),
    DRAW(bettingMoney -> 0),
    BLACKJACK(bettingMoney -> (int) (bettingMoney * 1.5));

    private final IntUnaryOperator operator;

    Result(IntUnaryOperator operator) {
        this.operator = operator;
    }

    public int calculateWinningAmount(int bettingMoneyValue) {
        return operator.applyAsInt(bettingMoneyValue);
    }
}
