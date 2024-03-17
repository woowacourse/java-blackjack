package domain;

import domain.gamer.Dealer;
import domain.gamer.Player;

public enum Result {
    WIN(1.0),
    LOSE(-1.0),
    TIE(0.0),
    BlACK_JACK(1.5);

    private final double dividend;

    Result(final double dividend) {
        this.dividend = dividend;
    }

    public static Result getPlayerResultWith(final Player player, final Dealer dealer) {
        if (isPlayerLose(player, dealer)) {
            return LOSE;
        }
        if (player.isBlackJack() && !dealer.isBlackJack()) {
            return BlACK_JACK;
        }
        if (isPlayerWin(player, dealer)) {
            return WIN;
        }
        return TIE;
    }

    private static boolean isPlayerLose(final Player player, final Dealer dealer) {
        if (player.isBust()) {
            return true;
        }
        if (!player.isBlackJack() && dealer.isBlackJack()) {
            return true;
        }
        if (player.calculateTotalScore() < dealer.calculateTotalScore() && !dealer.isBust()) {
            return true;
        }
        return false;
    }

    private static boolean isPlayerWin(final Player player, final Dealer dealer) {
        if (dealer.isBust()) {
            return true;
        }
        if (player.calculateTotalScore() > dealer.calculateTotalScore() && !player.isBust()) {
            return true;
        }
        return false;
    }

    public int calculateProfit(final int amount) {
        return (int) (amount * dividend);
    }
}
