package domain;

import domain.participant.Dealer;
import domain.participant.Player;

public enum GameResult {
    BLACKJACK_WIN(1.5),
    WIN(1.0),
    DRAW(0.0),
    LOSE(-1.0);

    public static int BLACKJACK_MAX_NUMBER = 21;
    private final double profitRate;

    GameResult(double profitRate) {
        this.profitRate = profitRate;
    }

    public static GameResult judge(Player player, Dealer dealer) {
        if (player.isBust()) {
            return LOSE;
        }

        if (player.isBlackjack()) {
            if (dealer.isBlackjack()) {
                return DRAW;
            }
            return BLACKJACK_WIN;
        }

        return compareScore(player, dealer);
    }

    private static GameResult compareScore(Player player, Dealer dealer) {
        if (dealer.isBust() || player.getTotalSum() > dealer.getTotalSum()) {
            return WIN;
        }
        if (player.getTotalSum() < dealer.getTotalSum()) {
            return LOSE;
        }
        return DRAW;
    }
    public int calculateProfit(int betAmount) {
        return (int) (betAmount * profitRate);
    }
}
