package blackjack.game;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum GameResult {
    WIN((dealerSum, playerSum) -> playerSum > dealerSum) {
        @Override
        public int calculateProfit(boolean isBlackjack, int principal) {
            if (isBlackjack) {
                return (int) (principal * BLACKJACK_PROFIT_RATE);
            }
            return principal;
        }
    },
    DRAW((dealerSum, playerSum) -> playerSum == dealerSum) {
        @Override
        public int calculateProfit(boolean isBlackjack, int principal) {
            return DRAW_PROFIT;
        }
    },
    LOSE((dealerSum, playerSum) -> playerSum < dealerSum) {
        @Override
        public int calculateProfit(boolean isBlackjack, int principal) {
            return -principal;
        }
    };

    private static final double BLACKJACK_PROFIT_RATE = 1.5;
    private static final int DRAW_PROFIT = 0;

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

    public abstract int calculateProfit(boolean isBlackjack, int principal);
}
