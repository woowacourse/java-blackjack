package blackjack.domain.game;

import java.util.function.UnaryOperator;

public enum ResultType {
    WIN(value -> value),
    WIN_WITH_BLACKJACK(value -> (int) Math.round(value * Constants.BLACKJACK_PROFIT_RATIO)),
    LOSE(value -> -value),
    DRAW(value -> 0);

    public final UnaryOperator<Integer> profitCalculator;

    ResultType(UnaryOperator<Integer> profitCalculator) {
        this.profitCalculator = profitCalculator;
    }

    private static class Constants {
        private static final double BLACKJACK_PROFIT_RATIO = 1.5;
    }
}
