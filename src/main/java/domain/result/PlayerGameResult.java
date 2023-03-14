package domain.result;

import java.util.function.UnaryOperator;

public enum PlayerGameResult {
    WIN(betAmount -> betAmount),
    DRAW(betAmount -> 0),
    LOSE(betAmount -> betAmount * -1),
    BLACKJACK(betAmount -> (int) (betAmount * 1.5));

    private final UnaryOperator<Integer> calculator;

    PlayerGameResult(final UnaryOperator<Integer> calculator) {
        this.calculator = calculator;
    }

    public int calculateBenefit(int betAmount) {
        return calculator.apply(betAmount);
    }
}
