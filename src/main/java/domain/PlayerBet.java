package domain;

public class PlayerBet {

    private final Player player;
    private final BettingAmount bettingAmount;

    private PlayerBet(Player player, BettingAmount bettingAmount) {
        this.player = player;
        this.bettingAmount = bettingAmount;
    }

    public static PlayerBet of(Player player, int amount) {
        return new PlayerBet(player, BettingAmount.of(amount));
    }

    public void applyProfitIfBlackjack() {
        if (player.isBlackjack()) {
            bettingAmount.applyBlackjackBonus();
        }
    }

    public long amount() {
        return bettingAmount.value();
    }
}
