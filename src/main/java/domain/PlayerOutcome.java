package domain;

public enum PlayerOutcome {
    BLACKJACK,
    WIN,
    DRAW,
    LOSS,
    ;

    public static PlayerOutcome of(Hand playerHand, Hand dealerHand) {
        if (playerHand.isBlackjack() && !dealerHand.isBlackjack()) {
            return BLACKJACK;
        }
        if (playerHand.isBust()) {
            return LOSS;
        }
        return winLossByScore(playerHand, dealerHand);
    }

    private static PlayerOutcome winLossByScore(final Hand playerHand, final Hand dealerHand) {
        final int playerScore = playerHand.calculateScore();
        final int dealerScore = dealerHand.calculateScore();
        if (dealerHand.isBust() || playerScore > dealerScore) {
            return WIN;
        }
        if (playerScore == dealerScore) {
            return DRAW;
        }
        return LOSS;
    }
}
