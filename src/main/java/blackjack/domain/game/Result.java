package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum Result {
    WIN,
    DRAW,
    LOSE;

    private static final int BLACKJACK_CHECK_SUM = 21;

    public static Result calculate(Player player, Dealer dealer) {
        int playerScore = calculateFinalScore(player.calculateScore());
        int dealerScore = calculateFinalScore(dealer.calculateScore());
        if (playerScore > dealerScore) {
            return WIN;
        }
        if (playerScore == dealerScore) {
            return DRAW;
        }
        return LOSE;
    }

    private static int calculateFinalScore(int sum) {
        if (sum > BLACKJACK_CHECK_SUM) {
            sum = 0;
        }
        return sum;
    }
}
