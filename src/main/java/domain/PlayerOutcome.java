package domain;

import java.util.function.IntUnaryOperator;

public enum PlayerOutcome {
    BLACKJACK(amount -> (int) (1.5 * amount)),
    WIN(amount -> amount),
    DRAW(amount -> 0),
    LOSS(amount -> -amount),
    ;

    private final IntUnaryOperator earningEquation;

    PlayerOutcome(final IntUnaryOperator earningEquation) {
        this.earningEquation = earningEquation;
    }

    public static PlayerOutcome of(final Hand playerHand, final Hand dealerHand) {
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

    public int calculateEarning(final int bettingAmount) {
        return earningEquation.applyAsInt(bettingAmount);
    }
}
