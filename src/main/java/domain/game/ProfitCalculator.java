package domain.game;

import java.util.function.BiFunction;

import domain.Number;
import view.ErrorMessage;

public enum ProfitCalculator {
    BLACKJACK(1.5, (handValue, dealerHandValue) -> handValue == Number.BLACKJACK_RESULT_VALUE.get()),
    WIN(1, (handValue, dealerHandValue) -> handValue - dealerHandValue > 0),
    LOSE(-1, (handValue, dealerHandValue) -> handValue == Number.BUST_VALUE.get() || handValue - dealerHandValue < 0),
    TIE(0, (handValue, dealerHandValue) -> handValue - dealerHandValue == 0);

    private final double ratio;
    private final BiFunction<Integer, Integer, Boolean> condition;

    ProfitCalculator(final double ratio, final BiFunction<Integer, Integer, Boolean> condition) {
        this.ratio = ratio;
        this.condition = condition;
    }

    public static double getRatio(final int handValue, final int dealerHandValue) {
        for (ProfitCalculator value : ProfitCalculator.values()) {
            if (value.condition.apply(handValue, dealerHandValue)) {
                return value.ratio;
            }
        }
        throw new IllegalStateException(ErrorMessage.NO_RESULT.getMessage());
    }

}
