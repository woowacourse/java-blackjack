package domain;

import java.util.function.UnaryOperator;

public enum GameState {
    WIN(betAmount -> betAmount),
    LOSE(betAmount -> betAmount.multiply(-1)),
    DRAW(betAmount -> BetAmount.getZero()),
    BLACKJACK(betAmount -> betAmount.multiply(1.5));

    private final UnaryOperator<BetAmount> calculator;

    GameState(UnaryOperator<BetAmount> calculator) {
        this.calculator = calculator;
    }

    public BetAmount calculate(BetAmount betAmount) {
        return calculator.apply(betAmount);
    }
}
