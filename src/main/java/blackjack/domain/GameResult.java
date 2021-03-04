package blackjack.domain;

public enum GameResult {

    WIN,
    LOSE,
    TIE;

    public static GameResult judgeHand(final int dealerScore, final int playerScore) {
        if (playerScore > 21 || dealerScore > playerScore) {
            return LOSE;
        }
        if (dealerScore < playerScore) {
            return WIN;
        }
        return TIE;
    }

    public static GameResult reverseResult(final GameResult gameResult) {
        if (gameResult == WIN) {
            return LOSE;
        }
        if (gameResult == LOSE) {
            return WIN;
        }
        return TIE;
    }
}
