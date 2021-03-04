package blackjack.domain;

public enum GameResult {

    WIN,
    LOSE,
    TIE;

    public GameResult judgeHand(final int dealerScore, final int playerScore) {
        if (playerScore > 21 || dealerScore > playerScore) {
            return LOSE;
        }
        if (dealerScore < playerScore) {
            return WIN;
        }
        return TIE;
    }
}
