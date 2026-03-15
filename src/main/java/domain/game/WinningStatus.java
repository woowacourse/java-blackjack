package domain.game;

import domain.participant.Dealer;
import domain.participant.Player;

import java.util.Optional;

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

        Optional<WinningStatus> winningStatus = judgeBlackjack(player, dealer);
        return winningStatus.orElseGet(() -> compareScore(player.score(), dealer.score()));
    }

    private static Optional<WinningStatus> judgeBlackjack(Player player, Dealer dealer) {
        boolean isPlayerBlackjack = player.isBlackjack();
        boolean isDealerBlackjack = dealer.isBlackjack();

        if (isPlayerBlackjack && !isDealerBlackjack) {
            return Optional.of(WIN);
        }

        if (!isPlayerBlackjack && isDealerBlackjack) {
            return Optional.of(LOSE);
        }

        if (isPlayerBlackjack && isDealerBlackjack) {
            return Optional.of(TIE);
        }

        return Optional.empty();
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
