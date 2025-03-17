package domain;

public class PlayerProfit {
    private final int playerProfit;
    private final PlayerName playerName;

    public PlayerProfit(Player player, Dealer dealer) {
        this.playerProfit = player.calculateProfit(dealer);
        this.playerName = player.getPlayerName();
    }

    public int getPlayerProfit() {
        return playerProfit;
    }

    public PlayerName getPlayerName() {
        return playerName;
    }
}
