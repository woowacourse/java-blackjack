package blackjack.game;

import java.util.Arrays;

public enum GameResult {
    WIN {
        @Override
        public boolean matches(final int dealerSum, final int playerSum) {
            return playerSum > dealerSum;
        }
    },
    DRAW {
        @Override
        public boolean matches(final int dealerSum, final int playerSum) {
            return playerSum == dealerSum;
        }
    },
    LOSE {
        @Override
        public boolean matches(final int dealerSum, final int playerSum) {
            return playerSum < dealerSum;
        }
    };

    public abstract boolean matches(final int dealerSum, final int playerSum);

    public static GameResult fromDenominationsSum(final int dealerSum, final int playerSum) {
        return Arrays.stream(values())
            .filter(result -> result.matches(dealerSum, playerSum))
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
