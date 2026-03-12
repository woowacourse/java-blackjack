package domain;

import domain.participant.Dealer;
import domain.participant.Player;

public enum WinningStatus {
    WIN, TIE, LOSE, BLACKJACK_WIN;

    public static final int BLACK_JACK = 21;
    public static final int INITIAL_CARD_COUNT = 2;

    public static WinningStatus of(Player player, Dealer dealer) {
        int playerScore = player.score();
        int dealerScore = dealer.score();

        if (playerScore > BLACK_JACK) {
            return LOSE;
        }
        if (isInitialBlackjack(player) && dealerScore != BLACK_JACK) {
            return BLACKJACK_WIN;
        }
        if (dealerScore > BLACK_JACK) {
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
        return player.handSize() == INITIAL_CARD_COUNT && player.score() == BLACK_JACK;
    }
}
