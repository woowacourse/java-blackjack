package domain;

public enum PlayerOutcome {
    WIN,
    DRAW,
    LOSE,
    ;

    public static PlayerOutcome of(int playerScore, int dealerScore) {
        if (playerScore > 21) {
            return LOSE;
        }
        if (dealerScore > 21) {
            return WIN;
        }
        if (playerScore > dealerScore) {
            return WIN;
        }
        if (playerScore < dealerScore) {
            return LOSE;
        }
        return DRAW;
    }
}
