package blackjack.model.results;

import java.util.function.IntUnaryOperator;

public enum Result {
    WIN_BY_BLACKJACK(money -> (int) (money * 1.5)),
    WIN(money -> money),
    PUSH(money -> 0),
    LOSE(money -> -money),
    LOSE_BY_BLACKJACK(money -> (int) -(money * 1.5));

    private final IntUnaryOperator operator;

    Result(IntUnaryOperator operator) {
        this.operator = operator;
    }

    public int getProfit(int money) {
        return operator.applyAsInt(money);
    }
}
