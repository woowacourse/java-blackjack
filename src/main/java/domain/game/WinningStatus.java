package domain.game;

import domain.participant.Dealer;
import domain.participant.Player;

public enum WinningStatus {
    WIN,
    TIE,
    LOSE;

    public static WinningStatus of(Player player, Dealer dealer) {
        if (dealer.isBust()) {
            return WIN;
        }

        if (player.isBust()) {
            return LOSE;
        }

        WinningStatus winningStatus = judgeBlackjack(player, dealer);
        if (winningStatus != null) {
            return winningStatus;
        }

        return compareScore(player.score(), dealer.score());
    }

    private static WinningStatus judgeBlackjack(Player player, Dealer dealer) {
        boolean isPlayerBlackjack = player.isBlackjack();
        boolean isDealerBlackjack = dealer.isBlackjack();

        if (isPlayerBlackjack && !isDealerBlackjack) {
            return WIN;
        }

        if (!isPlayerBlackjack && isDealerBlackjack) {
            return LOSE;
        }

        if (isPlayerBlackjack && isDealerBlackjack) {
            return TIE;
        }

        return null;
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
}
