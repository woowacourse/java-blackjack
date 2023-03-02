package domain;

public enum GameResult {
    LOSE,
    PUSH,
    WIN;

    public static GameResult comparePlayerWithDealer(int playerScore, int dealerScore) {
        if (playerScore < dealerScore) {
            return GameResult.LOSE;
        }
        if (playerScore == dealerScore) {
            return GameResult.PUSH;
        }
        return GameResult.WIN;
    }
}
