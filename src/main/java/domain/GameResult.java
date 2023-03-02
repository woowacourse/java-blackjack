package domain;

public enum GameResult {
    LOSE,
    PUSH,
    WIN;

    private static final int BLACK_JACK_SCORE = 21;

    public static GameResult comparePlayerWithDealer(int playerScore, int dealerScore) {
        if ((playerScore == dealerScore) || (playerScore > BLACK_JACK_SCORE && dealerScore > BLACK_JACK_SCORE)) {
            return PUSH;
        }
        if (playerScore < dealerScore) {
            return LOSE;
        }
        return WIN;
    }
}
