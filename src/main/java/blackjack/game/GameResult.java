package blackjack.game;

import blackjack.user.dealer.Dealer;
import blackjack.user.player.Player;
import java.util.Arrays;

public enum GameResult {
    WIN {
        @Override
        public boolean matches(int dealerSum, int playerSum) {
            return playerSum > dealerSum;
        }
    },
    DRAW {
        @Override
        public boolean matches(int dealerSum, int playerSum) {
            return playerSum == dealerSum;
        }
    },
    LOSE {
        @Override
        public boolean matches(int dealerSum, int playerSum) {
            return playerSum < dealerSum;
        }
    };

    public abstract boolean matches(int dealerSum, int playerSum);

    public static GameResult fromDenominationsSum(Dealer dealer, Player player) {
        int dealerSum = dealer.getCardHand().calculateDenominations();
        int playerSum = player.getCardHand().calculateDenominations();

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
