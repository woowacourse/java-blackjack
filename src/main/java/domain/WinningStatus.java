package domain;

import domain.participant.Dealer;
import domain.participant.Player;

public enum WinningStatus {
    WIN, TIE, LOSE, BLACKJACK_WIN;

    public static WinningStatus of(Player player, Dealer dealer) {
        int playerScore = player.score();
        int dealerScore = dealer.score();

        if (playerScore > BlackjackRule.BLACKJACK_SCORE) {
            return LOSE;
        }
        if (isInitialBlackjack(player) && dealerScore != BlackjackRule.BLACKJACK_SCORE) {
            return BLACKJACK_WIN;
        }
        if (dealerScore > BlackjackRule.BLACKJACK_SCORE) {
            return WIN;
        }
        return compareScore(playerScore, dealerScore);
    }

    private static WinningStatus compareScore(int playerScore, int dealerScore) {
        if (playerScore > dealerScore) {
            return WIN;
        }
        if (playerScore < dealerScore) {
            return LOSE;
        }
        return TIE;
    }

    private static boolean isInitialBlackjack(Player player) {
        return player.handSize() == BlackjackRule.INITIAL_CARD_COUNT
                && player.score() == BlackjackRule.BLACKJACK_SCORE;
    }
}
