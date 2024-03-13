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
        if (player.isBust() || dealer.isBust()) {
            return findResultBustCase(player, dealer);
        }
        if (player.isBlackJack() || dealer.isBlackJack()) {
            return findResultBlackJackCase(player, dealer);
        }
        return compareScoreWith(player, dealer);
    }

    private static Result findResultBustCase(final Player player, final Dealer dealer) {
        if (player.isBust() && !dealer.isBust()) {
            return LOSE;
        }
        if (!player.isBust() && dealer.isBust()) {
            return WIN;
        }
        return LOSE;
    }

    private static Result findResultBlackJackCase(final Player player, final Dealer dealer) {
        if (player.isBlackJack() && !dealer.isBlackJack()) {
            return BlACK_JACK;
        }
        if (!player.isBlackJack() && dealer.isBlackJack()) {
            return LOSE;
        }
        return TIE;
    }

    private static Result compareScoreWith(final Player player, final Dealer dealer) {
        int playerScore = player.calculateTotalScore();
        int dealerScore = dealer.calculateTotalScore();
        if (playerScore > dealerScore && !player.isBust()) {
            return WIN;
        }
        if (playerScore < dealerScore && !dealer.isBust()) {
            return LOSE;
        }
        return TIE;
    }

    public int calculateProfit(final int amount) {
        return (int) (amount * dividend);
    }
}
