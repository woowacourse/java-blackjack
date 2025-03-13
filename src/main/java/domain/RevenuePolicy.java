package domain;

import java.util.function.Function;

public enum RevenuePolicy {
    BLACKJACK_WIN(cost -> (int) (cost * 1.5)),
    NORMAL_WIN(cost -> cost),
    NORMAL_DRAW(cost -> cost),
    BLACKJACK_DRAW(cost -> cost),
    LOSE(cost -> -1 * cost),
    ;

    private final Function<Integer, Integer> function;

    RevenuePolicy(Function<Integer, Integer> function) {
        this.function = function;
    }

    public static RevenuePolicy from(GameResult gameResult, final boolean isPlayerBlackjack) {
        if (gameResult == GameResult.WIN && !isPlayerBlackjack) {
            return NORMAL_WIN;
        }
        if (gameResult == GameResult.WIN) {
            return BLACKJACK_WIN;
        }
        return null;
    }

    public int apply(final int bettingCost) {
        return function.apply(bettingCost);
    }
}
