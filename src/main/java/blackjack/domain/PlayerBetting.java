package blackjack.domain;

public class PlayerBetting {
    private final Player player;
    private final int bettingAmount;

    public PlayerBetting(Player player, int bettingAmount) {
        this.player = player;
        this.bettingAmount = bettingAmount;
    }

    public double calculateProfit(ScoreCompareResult compareResult) {
        if (compareResult == ScoreCompareResult.PLAYER_WIN) {
            return calculateProfitWhenPlayerWins();
        }
        return 0;
    }

    private double calculateProfitWhenPlayerWins() {
        if (player.isBlackjack()) {
            return (bettingAmount * 1.5);
        }
        return bettingAmount;
    }
}
