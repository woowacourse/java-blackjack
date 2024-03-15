package blackjack.domain.result;

import blackjack.domain.bet.Profit;
import blackjack.domain.player.Player;
import java.util.Map;

public class PlayerProfitResult {

    private final Map<Player, Profit> playerProfitMap;

    public PlayerProfitResult(Map<Player, Profit> playerProfitMap) {
        this.playerProfitMap = playerProfitMap;
    }

    public Profit findProfitOfPlayer(Player player) {
        return playerProfitMap.get(player);
    }

    public Profit calculateTotalProfit() {
        return playerProfitMap.values().stream()
                .reduce(new Profit(0), Profit::add);
    }

    public Profit calculateDealerProfit() {
        return calculateTotalProfit().inverse();
    }

    public Map<Player, Profit> getPlayerProfitMap() {
        return playerProfitMap;
    }
}
