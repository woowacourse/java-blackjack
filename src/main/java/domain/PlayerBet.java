package domain;

import java.math.BigDecimal;

public class PlayerBet {

    private final Player player;
    private final BettingAmount bettingAmount;

    private PlayerBet(Player player, BettingAmount bettingAmount) {
        this.player = player;
        this.bettingAmount = bettingAmount;
    }

    public static PlayerBet of(Player player, BigDecimal amount) {
        return new PlayerBet(player, BettingAmount.of(amount));
    }

    public boolean applyProfitIfBlackjackAndReturnApplied() {
        if (player.isBlackjack()) {
            bettingAmount.applyBlackjackBonus();
            return true;
        }
        return false;
    }

    public void applyProfitIfDealerBlackjack() {
        if (player.isBlackjack()) {
            bettingAmount.applyDrawAmount();
            return;
        }
        bettingAmount.applyLoseAmount();
    }

    public void IsBustLoseBettingAmount() {
        if (player.isBust()) {
            bettingAmount.applyLoseAmount();
        }
    }

    public void applyProfitByDealerScore(int dealerScore) {
        if (player.isBust()) {
            bettingAmount.applyLoseAmount();
            return;
        }

        int playerScore = player.calculateScore();
        if (dealerScore > playerScore) {
            bettingAmount.applyLoseAmount();
            return;
        }
        if (dealerScore == playerScore) {
            bettingAmount.applyDrawAmount();
        }
    }

    public BigDecimal amount() {
        return bettingAmount.value();
    }
}
