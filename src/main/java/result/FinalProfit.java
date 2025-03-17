package result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import participant.Dealer;
import participant.Player;

public final class FinalProfit {
    private final Map<Player, Profit> playerProfits;

    public FinalProfit(Dealer dealer, List<Player> players) {
        Map<Player, Profit> playerProfits = new HashMap<>();
        for (Player player : players) {
            GameResult result = dealer.judgeResult(player);
            playerProfits.put(player, player.calculateProfit(result));
        }
        this.playerProfits = playerProfits;
    }

    public Profit getProfit(final Player player) {
        return playerProfits.get(player);
    }

    public Profit calculateDealerProfit() {
        return playerProfits.values().stream()
                .reduce(Profit::add)
                .orElseThrow(IllegalStateException::new)
                .negative();
    }
}
