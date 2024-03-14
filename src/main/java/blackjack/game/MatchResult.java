package blackjack.game;

import blackjack.player.Score;

public enum MatchResult {

    DEALER_WIN,
    PLAYER_WIN,
    TIE;

    public static MatchResult chooseWinner(Score playerScore, Score dealerScore) {
        if (isPlayerWinningCondition(playerScore, dealerScore)) {
            return PLAYER_WIN;
        }
        if (isDealerWinningCondition(playerScore, dealerScore)) {
            return DEALER_WIN;
        }
        return TIE;
    }

    private static boolean isPlayerWinningCondition(Score playerScore, Score dealerScore) {
        if (playerScore.isBust()) {
            return false;
        }
        return dealerScore.isBust() || playerScore.isLargerThan(dealerScore);
    }

    private static boolean isDealerWinningCondition(Score playerScore, Score dealerScore) {
        if (playerScore.isBust()) {
            return true;
        }
        return dealerScore.isNotBust() && dealerScore.isLargerThan(playerScore);
    }
}
