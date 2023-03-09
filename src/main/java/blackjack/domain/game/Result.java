package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum Result {
    WIN,
    DRAW,
    LOSE;

    public static Result calculate(Player player, Dealer dealer) {
        int playerScore = player.calculateFinalScore();
        int dealerScore = dealer.calculateFinalScore();

        if (playerScore > dealerScore) {
            return WIN;
        }

        if (playerScore == dealerScore) {
            return DRAW;
        }
        return LOSE;
    }
}
