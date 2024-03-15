package blackjack.domain.result;

import blackjack.domain.bet.Profit;
import blackjack.domain.player.Player;
import java.util.Map;

public class PlayerProfits {

    private final Map<Player, Profit> playerProfits;

    public PlayerProfits(Map<Player, Profit> playerProfits) {
        this.playerProfits = playerProfits;
    }

    public Profit findProfitOfPlayer(Player player) {
        return playerProfits.get(player);
    }

    public Profit calculateTotalProfit() {
        return playerProfits.values().stream()
                .reduce(new Profit(0), Profit::add);
    }

    public Profit calculateDealerProfit() {
        return calculateTotalProfit().inverse();
    }

    public Map<Player, Profit> getPlayerProfits() {
        return playerProfits;
    }
}
