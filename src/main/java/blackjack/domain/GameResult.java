package blackjack.domain;

import java.util.function.BiFunction;

public enum GameResult {

    WIN((batting, isBlackjack) -> {
        if (isBlackjack) {
            return (int) (batting * 1.5);
        }

        return batting;
    }),
    DRAW((batting, isBlackjack) -> 0),
    LOSE((batting, isBlackjack) -> batting * -1),
    ;

    private final BiFunction<Integer, Boolean, Integer> profit;

    GameResult(BiFunction<Integer, Boolean, Integer> profit) {
        this.profit = profit;
    }

    public int getProfit(final int batting, final boolean isBlackjack) {
        return profit.apply(batting, isBlackjack);
    }
}
