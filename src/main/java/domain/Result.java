package domain;

import static domain.Constant.BLACKJACK_MAX_NUMBER;

public enum Result {
    WIN("승"), DRAW("무"), LOSE("패");

    private final String description;

    Result(String description) {
        this.description = description;
    }

    public static Result judge(int playerSum, int dealerSum) {
        if (isPlayerWin(playerSum, dealerSum)) {
            return WIN;
        }
        if (isPlayerLose(playerSum, dealerSum)) {
            return LOSE;
        }
        return DRAW;
    }

    public String getDescription() {
        return description;
    }

    private static boolean isPlayerWin(int playerSum, int dealerSum) {
        return playerSum <= BLACKJACK_MAX_NUMBER && playerSum > dealerSum;
    }

    private static boolean isPlayerLose(int playerSum, int dealerSum) {
        return (playerSum < dealerSum && dealerSum <= BLACKJACK_MAX_NUMBER) || (playerSum > BLACKJACK_MAX_NUMBER
                && dealerSum <= BLACKJACK_MAX_NUMBER);
    }
}
