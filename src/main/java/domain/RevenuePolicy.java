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
        if (gameResult == GameResult.DRAW && isPlayerBlackjack) {
            return BLACKJACK_DRAW;
        }
        if (gameResult == GameResult.DRAW) {
            return NORMAL_DRAW;
        }
        if (gameResult == GameResult.LOSE && isPlayerBlackjack) {
            throw new IllegalArgumentException("플레이어가 블랙잭인 경우에는 패배할 수 없습니다.");
        }
        if (gameResult == GameResult.LOSE) {
            return LOSE;
        }
        return null;
    }

    public int apply(final int bettingCost) {
        return function.apply(bettingCost);
    }
}
