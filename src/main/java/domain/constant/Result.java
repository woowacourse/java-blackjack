package domain.constant;

import domain.participant.Dealer;
import domain.participant.Player;

public enum Result {
    BUST,
    BLACKJACK,
    WIN,
    LOSE,
    DRAW;

    public static Result from(Player player, Dealer dealer) {
        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();

        if (player.isBust()) {
            return BUST;
        }
        if (player.isNaturalBlackJack() && dealer.isNaturalBlackJack()) {
            return DRAW;
        }
        if (player.isNaturalBlackJack()) {
            return BLACKJACK;
        }
        if (dealer.isBust()) {
            return WIN;
        }
        if (playerScore > dealerScore) {
            return WIN;
        }
        if (playerScore < dealerScore) {
            return LOSE;
        }
        return DRAW;
    }
}