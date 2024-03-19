package blackjack.game;

import blackjack.player.Hand;
import blackjack.player.Score;

public enum MatchResult {

    DEALER_WIN(-1),
    PLAYER_WIN(1),
    PLAYER_BLACKJACK(1.5),
    TIE(0);

    private final double prizeRate;

    MatchResult(double prizeRate) {
        this.prizeRate = prizeRate;
    }

    public static double calculatePrizeRate(Hand playerHand, Hand dealerHand) {
        if (isPlayerBlackJack(playerHand, dealerHand)) {
            return PLAYER_BLACKJACK.prizeRate;
        }
        if (isPlayerWinning(playerHand, dealerHand)) {
            return PLAYER_WIN.prizeRate;
        }
        if (isDealerWinning(playerHand, dealerHand)) {
            return DEALER_WIN.prizeRate;
        }
        return TIE.prizeRate;
    }

    private static boolean isPlayerBlackJack(Hand playerHand, Hand dealerHand) {
        return playerHand.isBlackJack() && !dealerHand.isBlackJack();
    }

    private static boolean isPlayerWinning(Hand playerHand, Hand dealerHand) {
        Score playerScore = playerHand.calculateScore();
        Score dealerScore = dealerHand.calculateScore();

        if (playerScore.isBust()) {
            return false;
        }
        return dealerScore.isBust() || playerScore.isLargerThan(dealerScore);
    }

    private static boolean isDealerWinning(Hand playerHand, Hand dealerHand) {
        Score playerScore = playerHand.calculateScore();
        Score dealerScore = dealerHand.calculateScore();

        if (playerScore.isBust()) {
            return true;
        }
        if (dealerHand.isBlackJack() && !playerHand.isBlackJack()) {
            return true;
        }
        return dealerScore.isNotBust() && dealerScore.isLargerThan(playerScore);
    }

    double getPrizeRate() {
        return prizeRate;
    }
}
