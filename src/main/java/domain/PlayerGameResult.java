package domain;

public enum PlayerGameResult {
    WIN,
    DRAW,
    LOSE;

    private static final int BLACKJACK = 21;

    public static PlayerGameResult of(int playerScore, int dealerScore) {
        if (playerScore > BLACKJACK) {
            return LOSE;
        }
        if (dealerScore > BLACKJACK || playerScore > dealerScore) {
            return WIN;
        }
        if (playerScore == dealerScore) {
            return DRAW;
        }

        return LOSE;
    }
}
