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

    public void applyProfitIfBlackjack() {
        if (player.isBlackjack()) {
            bettingAmount.applyBlackjackBonus();
        }
    }

    public BigDecimal amount() {
        return bettingAmount.value();
    }

    public void loseBettingAmountIsBust() {
        if (player.isBust()) {
            bettingAmount.applyLoseAmount();
        }
    }
}
