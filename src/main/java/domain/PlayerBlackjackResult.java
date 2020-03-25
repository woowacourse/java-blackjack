package domain;

import domain.user.Dealer;
import domain.user.Player;

public enum PlayerBlackjackResult {
    BLACKJACK_WIN(1.5),
    BUST_WIN(1),
    SCORE_WIN(1),
    LOSE(-1),
    DRAW(0);

    private double profitRate;

    PlayerBlackjackResult(double profitRate) {
        this.profitRate = profitRate;
    }

    public static PlayerBlackjackResult from(Player player, Dealer dealer) {
        if (player.isBlackjack() && !dealer.isBlackjack()) {
            return BLACKJACK_WIN;
        }
        if (player.isNotBust() && dealer.isBust()) {
            return BUST_WIN;
        }
        if (player.isNotBust() && player.getScore() > dealer.getScore()) {
            return SCORE_WIN;
        }
        if (player.isBust() && dealer.isBust() || player.isBlackjack() && dealer.isBlackjack() || player.getScore() == dealer.getScore()) {
            return DRAW;
        }
        return LOSE;
    }

    public double getProfitRate() {
        return profitRate;
    }
}
