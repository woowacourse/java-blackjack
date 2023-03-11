package domain;

import java.util.function.IntUnaryOperator;

public enum BlackJackResult {
    WIN(money -> money),
    LOSE(money -> money * -1),
    BLACKJACK(money -> (int) (money * 1.5)),
    EACH_BLACKJACK(money -> 0);

    private final IntUnaryOperator expression;

    BlackJackResult(IntUnaryOperator expression) {
        this.expression = expression;
    }

    public int calculatePrize(int money) {
        return expression.applyAsInt(money);
    }
}
