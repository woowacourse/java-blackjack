package blackjack.domain.result;

public enum GameResult {

    WIN,
    DRAW,
    LOSE,
    ;

    private static final int BUST_THRESHOLD = 21;

    public static GameResult of(final int playerScore, final int dealerScore) {
        if (playerScore > BUST_THRESHOLD) {
            return LOSE;
        }
        if (dealerScore > BUST_THRESHOLD) {
            return WIN;
        }
        return compare(playerScore, dealerScore);
    }

    private static GameResult compare(final int playerScore, final int dealerScore) {
        if (playerScore > dealerScore) {
            return WIN;
        }
        if (playerScore < dealerScore) {
            return LOSE;
        }
        return DRAW;
    }

    public GameResult reverse() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }
}
