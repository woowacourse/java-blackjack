package domain;

public enum GameResultStatus {
    WIN,
    DRAW,
    LOSE;

    public static GameResultStatus calculate(int dealerSum, int playerSum) {
        if(playerSum >= 22) {
            return GameResultStatus.LOSE;
        }
        if(playerSum > dealerSum) {
            return GameResultStatus.WIN;
        }
        if(playerSum == dealerSum) {
            return GameResultStatus.DRAW;
        }
        return GameResultStatus.LOSE;
    }
}
