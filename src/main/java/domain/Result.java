package domain;

public enum Result {
    WIN, DRAW, LOSE;
    public static int BLACKJACK_MAX_NUMBER = 21;

    public static Result judge(int playerSum, int dealerSum) {
        if (isPlayerWin(playerSum, dealerSum)) {
            return WIN;
        }
        if (isPlayerLose(playerSum, dealerSum)) {
            return LOSE;
        }
        return DRAW;
    }

    private static boolean isPlayerWin(int playerSum, int dealerSum) {
        return playerSum <= BLACKJACK_MAX_NUMBER && playerSum > dealerSum;
    }

    private static boolean isPlayerLose(int playerSum, int dealerSum) {
        return (playerSum < dealerSum && dealerSum <= BLACKJACK_MAX_NUMBER) || (playerSum > BLACKJACK_MAX_NUMBER
                && dealerSum <= BLACKJACK_MAX_NUMBER);
    }
}
