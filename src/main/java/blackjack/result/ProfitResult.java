package blackjack.result;

import blackjack.gamer.Player;
import java.util.Map;

public class ProfitResult {
    private final int dealerProfit;
    private final Map<Player, Integer> playerProfits;

    public ProfitResult(int dealerProfit, Map<Player, Integer> playerProfits) {
        this.dealerProfit = dealerProfit;
        this.playerProfits = playerProfits;
    }

    public int getDealerProfit() {
        return dealerProfit;
    }

    public Map<Player, Integer> getPlayerProfits() {
        return playerProfits;
    }
}
