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

    public static WinStatus from(boolean winStatus) {
        if (winStatus) {
            return WIN;
        }
        return LOSE;
    }

    public static WinStatus winStatusByPlayer(Player player, Dealer dealer) {
        if (player.isBust()) {
            return WinStatus.LOSE;
        }
        if (dealer.isBust()) {
            return WinStatus.WIN;
        }
        return getWinStatusWhenNotBust(player, dealer);
    }

    private static WinStatus getWinStatusWhenNotBust(Player player, Dealer dealer) {
        int participantScore = player.getScore();
        int dealerScore = dealer.getScore();

        if (participantScore == dealerScore) {
            return WinStatus.PUSH;
        }
        if (player.isBlackJack()) {
            return WinStatus.BLACKJACK;
        }
        return WinStatus.from(participantScore > dealerScore);
    }

    public double getProfitMultiplier() {
        return profitMultiplier;
    }
}
