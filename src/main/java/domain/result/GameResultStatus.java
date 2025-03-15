package domain.result;

public enum GameResultStatus {
    WIN,
    DRAW,
    LOSE;

    private static final int BLACKJACK_BUST_THRESHOLD = 22;

    public static GameResultStatus calculate(int dealerSum, int playerSum) {
        if (isPlayerBust(playerSum)) {
            return LOSE;
        }
        if (isDealerBust(dealerSum)) {
            return WIN;
        }
        return compareHands(dealerSum, playerSum);
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
