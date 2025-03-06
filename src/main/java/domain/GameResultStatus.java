package domain;

public enum GameResultStatus {
    WIN,
    DRAW,
    LOSE;

    private static final int BLACKJACK_BUST_THRESHOLD = 22;

    public static GameResultStatus calculate(int dealerSum, int playerSum) {
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

    private static boolean isBothBust(int dealerSum, int playerSum) {
        return isDealerBust(dealerSum) && isPlayerBust(playerSum);
    }

    private static boolean isDealerBust(int dealerSum) {
        return dealerSum >= BLACKJACK_BUST_THRESHOLD;
    }

    private static boolean isPlayerBust(int playerSum) {
        return playerSum >= BLACKJACK_BUST_THRESHOLD;
    }

    private static GameResultStatus compareHands(int dealerSum, int playerSum) {
        if (playerSum > dealerSum) {
            return WIN;
        }
        if (playerSum == dealerSum) {
            return DRAW;
        }
        return LOSE;
    }

    public boolean isEqualTo(GameResultStatus gameResultStatus) {
        return gameResultStatus == this;
    }
}
