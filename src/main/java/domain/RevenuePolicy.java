package domain;

import java.util.Arrays;
import java.util.function.Function;

public enum RevenuePolicy {
    BLACKJACK_WIN(GameResult.WIN, true, cost -> (int) (cost * 1.5)),
    NORMAL_WIN(GameResult.WIN, false, cost -> cost),
    BLACKJACK_DRAW(GameResult.DRAW, true, cost -> cost),
    NORMAL_DRAW(GameResult.DRAW, false, cost -> cost),
    LOSE(GameResult.LOSE, false, cost -> -1 * cost),
    ;

    private final GameResult gameResult;
    private final boolean isPlayerBlackjack;
    private final Function<Integer, Integer> function;

    RevenuePolicy(GameResult gameResult, boolean isPlayerBlackjack, Function<Integer, Integer> function) {
        this.gameResult = gameResult;
        this.isPlayerBlackjack = isPlayerBlackjack;
        this.function = function;
    }

    public static RevenuePolicy from(GameResult gameResult, final boolean isPlayerBlackjack) {
        return Arrays.stream(values())
                .filter(policy -> policy.gameResult == gameResult && policy.isPlayerBlackjack == isPlayerBlackjack)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("플레이어가 블랙잭인 경우에는 패배할 수 없습니다."));
    }

    public int apply(final int bettingCost) {
        return function.apply(bettingCost);
    }
}
