package domain;

import java.util.function.Function;

public enum BlackJackResult {

    WIN(money -> money),
    LOSE(money -> money * -1),
    BLACKJACK(money -> money * 1.5),
    EACH_BLACKJACK(money -> money),
    BURST(money -> money * -1);

    private Function<Double, Double> expression;

    BlackJackResult(Function<Double, Double> expression) {
        this.expression = expression;
    }

    public double calculatePrize(double money) {
        return expression.apply(money);
    }
}
