package blackjack.dto.response;

public class PlayerGameResult {
    private final String playerName;
    private final int profit;

    public PlayerGameResult(String playerName, int profit) {
        this.playerName = playerName;
        this.profit = profit;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getProfit() {
        return profit;
    }
}
