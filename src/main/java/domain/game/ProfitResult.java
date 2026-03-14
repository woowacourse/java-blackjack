package domain.game;

import domain.participant.Player;
import java.util.Map;

public class ProfitResult {
    private final Map<Player, Integer> playerProfits;

    public ProfitResult(Map<Player, Integer> playerProfits) {
        this.playerProfits = playerProfits;
    }

    public Map<Player, Integer> getPlayerProfits() {
        return playerProfits;
    }

    public int getDealerProfit() {
        int dealerProfit = 0;
        for (int profit : playerProfits.values()) {
            dealerProfit -= profit;
        }
        return dealerProfit;
    }
}
