package blackjack.game;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum GameResult {
    WIN((dealerSum, playerSum) -> playerSum > dealerSum),
    DRAW((dealerSum, playerSum) -> playerSum == dealerSum),
    LOSE((dealerSum, playerSum) -> playerSum < dealerSum);

    private final BiPredicate<Integer, Integer> compareSum;

    GameResult(BiPredicate<Integer, Integer> compareSum) {
        this.compareSum = compareSum;
    }

    public static GameResult fromDenominationsSum(final int dealerSum, final int playerSum) {
        return Arrays.stream(values())
            .filter(result -> result.compareSum.test(dealerSum, playerSum))
            .findFirst()
            .orElseThrow();
    }

    public boolean isWin() {
        return this == WIN;
    }

    public boolean isDraw() {
        return this == DRAW;
    }

    public boolean isLose() {
        return this == LOSE;
    }
}
