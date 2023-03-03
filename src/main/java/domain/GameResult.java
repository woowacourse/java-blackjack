package domain;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum GameResult {
    WIN((playerPoint, dealerPoint) -> playerPoint > dealerPoint),
    LOSE((playerPoint, dealerPoint) -> playerPoint < dealerPoint),
    DRAW(Integer::equals);

    private final BiFunction<Integer, Integer, Boolean> function;

    GameResult(BiFunction<Integer, Integer, Boolean> function) {
        this.function = function;
    }

    public static GameResult getResult(Player player, Dealer dealer) {
        return Arrays.stream(GameResult.values())
                .filter(it -> it.function.apply(getPlayerPoint(player), getPlayerPoint(dealer)))
                .findAny()
                .orElseThrow();
    }

    private static int getPlayerPoint(Player player) {
        if (player.isOverCardPointLimit()) {
            return 0;
        }
        return player.sumCardPool();
    }
}
