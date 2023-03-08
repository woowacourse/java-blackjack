package domain;

import java.util.function.Function;

public enum BlackJackResult {

    WIN(money -> money),
    LOSE(money -> money * -1),
    BLACKJACK(money -> (int) (money * 1.5)),
    EACH_BLACKJACK(money -> 0),
    BURST(money -> money * -1);

    private Function<Integer, Integer> expression;

    BlackJackResult(Function<Integer, Integer> expression) {
        this.expression = expression;
    }

    public int calculatePrize(int money) {
        return expression.apply(money);
    }
}
