package blackjack.game;

public enum MatchResult {

    LOSE,
    NORMAL_WIN,
    BLACKJACK_WIN,
    TIE,
    ;

    public static MatchResult chooseWinner(int playerScore, int dealerScore) {
        if (isPlayerWinningCondition(playerScore, dealerScore)) {
            return NORMAL_WIN;
        }
        if (isDealerWinningCondition(playerScore, dealerScore)) {
            return LOSE;
        }
        return TIE;
    }

    private static boolean isPlayerWinningCondition(int playerScore, int dealerScore) {
        if (isBust(playerScore)) {
            return false;
        }
        return isBust(dealerScore) || playerScore > dealerScore;
    }

    private static boolean isDealerWinningCondition(int playerScore, int dealerScore) {
        if (isBust(playerScore)) {
            return true;
        }
        return !isBust(dealerScore) && dealerScore > playerScore;
    }

    private static boolean isBust(int score) {
        return score > BlackJackGame.BLACKJACK_MAX_SCORE;
    }
}
