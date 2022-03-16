package blackjack.dto.response;

public class PlayerGameResult {
    private final String playerName;
    private final double profit;

    public PlayerGameResult(String playerName, double profit) {
        this.playerName = playerName;
        this.profit = profit;
    }

    public String getPlayerName() {
        return playerName;
    }

    public double getProfit() {
        return profit;
    }
}
