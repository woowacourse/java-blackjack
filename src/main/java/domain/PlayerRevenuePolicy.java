package domain;

import java.util.Arrays;
import java.util.function.Function;

public enum PlayerRevenuePolicy {
    BLACKJACK_WIN(GameResult.WIN, true, cost -> (int) (cost * 1.5)),
    NORMAL_WIN(GameResult.WIN, false, cost -> cost),
    BLACKJACK_DRAW(GameResult.DRAW, true, cost -> 0),
    NORMAL_DRAW(GameResult.DRAW, false, cost -> 0),
    LOSE(GameResult.LOSE, false, cost -> -1 * cost),
    ;

    private final GameResult gameResult;
    private final boolean isPlayerBlackjack;
    private final Function<Integer, Integer> function;

    PlayerRevenuePolicy(GameResult gameResult, boolean isPlayerBlackjack, Function<Integer, Integer> function) {
        this.gameResult = gameResult;
        this.isPlayerBlackjack = isPlayerBlackjack;
        this.function = function;
    }

    public static PlayerRevenuePolicy from(GameResult gameResult, final boolean isPlayerBlackjack) {
        return Arrays.stream(values())
                .filter(policy -> policy.gameResult == gameResult && policy.isPlayerBlackjack == isPlayerBlackjack)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("플레이어가 블랙잭인 경우에는 패배할 수 없습니다."));
    }

    public int getRevenue(final int bettingCost) {
        return function.apply(bettingCost);
    }
}
