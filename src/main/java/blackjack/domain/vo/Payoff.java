package blackjack.domain.vo;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum Payoff {
    BLACKJACK(1.5),
    WIN(1.0),
    DRAW(0.0),
    LOSE(-1.0);

    private final double earningRate;

    Payoff(double earningRate) {
        this.earningRate = earningRate;
    }

    public static Payoff playerResult(Player player, Dealer dealer) {
        if (player.isBust()) {
            return LOSE;
        }

        if (player.isBlackjack() && dealer.isBlackjack()) {
            return DRAW;
        }

        if (player.isBlackjack()) {
            return BLACKJACK;
        }

        if (dealer.isBlackjack()) {
            return LOSE;
        }

        if (dealer.isBust()) {
            return WIN;
        }

        if (player.hasHigherScoreThan(dealer)) {
            return WIN;
        }

        if (dealer.hasHigherScoreThan(player)) {
            return LOSE;
        }

        return DRAW;
    }

    public int calculateProfit(int betAmount) {
        return (int) (betAmount * earningRate);
    }
}
