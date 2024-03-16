package domain.blackjack;

import domain.participant.Dealer;
import domain.participant.Player;

public enum WinStatus {

    WIN(1),
    BLACKJACK(1.5),
    PUSH(0),
    LOSE(-1);

    private final double profitMultiplier;

    WinStatus(double profitMultiplier) {
        this.profitMultiplier = profitMultiplier;
    }

    public static WinStatus winStatusOfPlayer(Player player, Dealer dealer) {
        if (player.isBust()) {
            return WinStatus.LOSE;
        }
        if (dealer.isBust()) {
            return WinStatus.WIN;
        }
        return winStatusWhenNotBust(player, dealer);
    }

    private static WinStatus winStatusWhenNotBust(Player player, Dealer dealer) {
        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();

        if (playerScore == dealerScore) {
            return WinStatus.PUSH;
        }
        if (player.isBlackJack()) {
            return WinStatus.BLACKJACK;
        }
        return WinStatus.winStatusWinOrLose(playerScore > dealerScore);
    }

    private static WinStatus winStatusWinOrLose(boolean isPlayerScoreGreater) {
        if (isPlayerScoreGreater) {
            return WIN;
        }
        return LOSE;
    }

    public double getProfitMultiplier() {
        return profitMultiplier;
    }
}
