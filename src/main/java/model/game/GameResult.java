package model.game;

import model.participant.Dealer;
import model.participant.Player;

public enum GameResult {
    BLACKJACK(1.5),
    WIN(1.0),
    PUSH(0.0),
    LOSE(-1.0),
    ;

    private final double profitRate;

    GameResult(double profitRate) {
        this.profitRate = profitRate;
    }

    public static GameResult calculateResult(Dealer dealer, Player player) {
        if (player.isBlackjack()) {
            if (!dealer.isBlackjack()) {
                return BLACKJACK;
            }

            return WIN;
        }

        if (player.isBust()) {
            return LOSE;
        }

        if (dealer.isBust()) {
            return WIN;
        }

        if (dealer.isBlackjack()) {
            return LOSE;
        }

        return calculateFromScore(dealer, player);
    }

    public long getProfitFrom(BettingAmount amount) {
        return Math.round(profitRate * amount.getAmount());
    }

    private static GameResult calculateFromScore(Dealer dealer, Player player) {
        if (dealer.calculateScore() > player.calculateScore()) {
            return LOSE;
        }

        if (dealer.calculateScore() == player.calculateScore()) {
            return PUSH;
        }

        return WIN;
    }
}
