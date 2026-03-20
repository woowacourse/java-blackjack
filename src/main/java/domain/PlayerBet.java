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

    public void applyResult(Dealer dealer) {
        if (dealer.isBlackjack()) {
            applyProfitIfDealerBlackjack();
            return;
        }
        if (applyProfitIfBlackjackAndReturnApplied()) {
            return;
        }
        if (dealer.isBust()) {
            IsBustLoseBettingAmount();
            return;
        }
        applyProfitByDealerScore(dealer.calculateScore());
    }

    private void applyProfitIfDealerBlackjack() {
        if (player.isBlackjack()) {
            bettingAmount.applyDrawAmount();
            return;
        }
        bettingAmount.applyLoseAmount();
    }

    private boolean applyProfitIfBlackjackAndReturnApplied() {
        if (player.isBlackjack()) {
            bettingAmount.applyBlackjackBonus();
            return true;
        }
        return false;
    }

    private void IsBustLoseBettingAmount() {
        if (player.isBust()) {
            bettingAmount.applyLoseAmount();
        }
    }

    private void applyProfitByDealerScore(int dealerScore) {
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
