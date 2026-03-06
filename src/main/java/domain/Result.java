package domain;

public enum Result {
    WIN, DRAW, LOSE;
    public static Result judge(int playerSum, int dealerSum) {
        if(isPlayerWin(playerSum, dealerSum)){
            return WIN;
        }
        if(isPlayerLose(playerSum, dealerSum)){
            return LOSE;
        }
        return DRAW;
    }

    private static boolean isPlayerWin(int playerSum, int dealerSum) {
        return playerSum < 22 && playerSum > dealerSum;
    }

    private static boolean isPlayerLose(int playerSum, int dealerSum) {
        return (playerSum < dealerSum && dealerSum < 22) || (playerSum > 21 && dealerSum < 22);
    }
}
