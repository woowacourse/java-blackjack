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

    public static GameResult getResult(int playerPoint, int dealerPoint) {
        return Arrays.stream(GameResult.values())
                .filter(it -> it.function.apply(playerPoint, dealerPoint))
                .findAny()
                .orElseThrow();
    }
}
