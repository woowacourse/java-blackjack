package blackjack.domain.result;

import java.util.function.UnaryOperator;

public enum GameResult {

    VICTORY(betValue -> betValue * 2),
    BLACKJACK(betValue -> (int) (betValue * 2.5)),
    PUSH(betValue -> betValue),
    DEFEAT(betValue -> 0),
    ;

    private final UnaryOperator<Integer> calculator;

    GameResult(UnaryOperator<Integer> calculator) {
        this.calculator = calculator;
    }

    public int getWinningAmount(final int betValue) {
        return calculator.apply(betValue);
    }
}
