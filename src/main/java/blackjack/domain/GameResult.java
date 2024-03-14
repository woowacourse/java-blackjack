package blackjack.domain;

import java.util.function.BiFunction;

public enum GameResult {

    WIN((betting, isBlackjack) -> {
        if (isBlackjack) {
            return (int) (betting * 1.5);
        }

        return betting;
    }),
    DRAW((betting, isBlackjack) -> 0),
    LOSE((betting, isBlackjack) -> betting * -1),
    ;

    private final BiFunction<Integer, Boolean, Integer> profit;

    GameResult(BiFunction<Integer, Boolean, Integer> profit) {
        this.profit = profit;
    }

    public int getProfit(final int betting, final boolean isBlackjack) {
        return profit.apply(betting, isBlackjack);
    }
}
