package domain;

public enum GameResultStatus {
    WIN,
    DRAW,
    LOSE;

    public static GameResultStatus calculate(int dealerSum, int playerSum) {
        if (dealerSum >= 22 && playerSum >= 22) {
            return GameResultStatus.DRAW;
        }
        if(dealerSum >= 22) {
            return GameResultStatus.WIN;
        }
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

    public boolean isEqualTo(GameResultStatus gameResultStatus) {
        return gameResultStatus == this;
    }
}
