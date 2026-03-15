package model.game;

import java.math.BigDecimal;
import java.math.RoundingMode;
import model.participant.Dealer;
import model.participant.Player;

public enum GameResult {
    BLACKJACK(new BigDecimal("1.5")),
    WIN(new BigDecimal("1.0")),
    PUSH(new BigDecimal("0.0")),
    LOSE(new BigDecimal("-1.0")),
    ;

    private final BigDecimal profitRate;

    GameResult(BigDecimal profitRate) {
        this.profitRate = profitRate;
    }

    public static GameResult calculateResult(Dealer dealer, Player player) {
        if (player.isBlackjack() || dealer.isBlackjack()) {
            return calculateFromBlackjackCase(dealer, player);
        }

        if (player.isBust() || dealer.isBust()) {
            return calculateFromBustCase(player);
        }

        return calculateFromScore(dealer, player);
    }

    private static GameResult calculateFromBlackjackCase(Dealer dealer, Player player) {
        if (player.isBlackjack() && dealer.isBlackjack()) {
            return PUSH;
        }

        if (player.isBlackjack()) {
            return BLACKJACK;
        }

        return LOSE;
    }

    private static GameResult calculateFromBustCase(Player player) {
        if (player.isBust()) {
            return LOSE;
        }

        return WIN;
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

    public BigDecimal getProfitFrom(BettingAmount amount) {
        return amount.getAmount()
                .multiply(profitRate)
                .setScale(0, RoundingMode.DOWN);
    }
}
