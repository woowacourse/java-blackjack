package game;

public enum MatchResult {

    DEALER_WIN,
    PLAYER_WIN,
    TIE;

    public static MatchResult chooseWinner(int playerScore, int dealerScore) {
        if (isBurst(playerScore)) {
            return DEALER_WIN;
        }
        if (isBurst(dealerScore)) {
            return PLAYER_WIN;
        }
        if (dealerScore == playerScore) {
            return TIE;
        }
        if (dealerScore > playerScore) {
            return DEALER_WIN;
        }
        return PLAYER_WIN;
    }

    private static boolean isBurst(int score) {
        return score > BlackJackGame.BLACKJACK_MAX_SCORE;
    }
}
