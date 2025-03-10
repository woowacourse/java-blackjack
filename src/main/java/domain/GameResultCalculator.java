package domain;

import static domain.GameResultStatus.DRAW;
import static domain.GameResultStatus.LOSE;
import static domain.GameResultStatus.WIN;

public class GameResultCalculator {

    private static final int BLACKJACK_BUST_THRESHOLD = 21;

    public GameResultStatus calculate(int dealerSum, int playerSum) {
        if (isBothBust(dealerSum, playerSum)) {
            return DRAW;
        }
        if (isDealerBust(dealerSum)) {
            return WIN;
        }
        if (isPlayerBust(playerSum)) {
            return LOSE;
        }
        return compareHands(dealerSum, playerSum);
    }

    private boolean isBothBust(int dealerSum, int playerSum) {
        return isDealerBust(dealerSum) && isPlayerBust(playerSum);
    }

    private boolean isDealerBust(int dealerSum) {
        return dealerSum > BLACKJACK_BUST_THRESHOLD;
    }

    private boolean isPlayerBust(int playerSum) {
        return playerSum > BLACKJACK_BUST_THRESHOLD;
    }

    private GameResultStatus compareHands(int dealerSum, int playerSum) {
        if (playerSum > dealerSum) {
            return WIN;
        }
        if (playerSum == dealerSum) {
            return DRAW;
        }
        return LOSE;
    }
}
