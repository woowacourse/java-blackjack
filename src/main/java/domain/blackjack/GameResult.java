package domain.blackjack;

import java.util.function.Function;

public enum GameResult {
    LOSE(bettingMoney -> -bettingMoney),
    WIN(bettingMoney -> bettingMoney),
    TIE(bettingMoney -> 0),
    WIN_BLACK_JACK(bettingMoney -> (int) (bettingMoney * 1.5));
    private final Function<Integer, Integer> earnMoneyCalculator;

    GameResult(Function<Integer, Integer> earnMoneyCalculator) {
        this.earnMoneyCalculator = earnMoneyCalculator;
    }

    GameResult changeBase() {
        if (this == WIN || this == WIN_BLACK_JACK) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return TIE;
    }

    int calculateEarnMoney(int bettingMoney) {
        return earnMoneyCalculator.apply(bettingMoney);
    }
}
