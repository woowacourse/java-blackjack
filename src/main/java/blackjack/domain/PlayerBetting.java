package blackjack.domain;

public class PlayerBetting {
    private final Player player;
    private final int bettingAmount;

    public PlayerBetting(Player player, int bettingAmount) {
        this.player = player;
        this.bettingAmount = bettingAmount;
    }

    public int calculateProfit(ScoreCompareResult compareResult) {
        if (compareResult == ScoreCompareResult.PLAYER_WIN) {
            return bettingAmount;
        }
        return 0;
    }
}
