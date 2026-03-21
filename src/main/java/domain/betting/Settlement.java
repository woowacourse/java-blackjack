package domain.betting;

import domain.participant.Name;
import java.util.Map;

public class Settlement {
    private final int dealerProfit;
    private final Map<Name, Integer> playerProfits;

    public Settlement(int dealerProfit, Map<Name, Integer> playerProfits) {
        this.dealerProfit = dealerProfit;
        this.playerProfits = playerProfits;
    }

    public int getDealerProfit() {
        return dealerProfit;
    }

    public Map<Name, Integer> getPlayerProfits() {
        return playerProfits;
    }
}