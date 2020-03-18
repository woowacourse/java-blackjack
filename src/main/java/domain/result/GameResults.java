package domain.result;

import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.Players;

import java.util.HashMap;
import java.util.Map;

public class GameResults {
    private final Map<Player, PlayerResult> gameResults = new HashMap<>();

    public GameResults(Dealer dealer, Players players) {
        for (Player player : players) {
            gameResults.put(player, PlayerResult.match(dealer, player));
        }
    }

    public int calculateEarning(Player player) {
        return player.calculateEarning(gameResults.get(player));
    }

    public int calculateTotalDealerEarning() {
        return gameResults.keySet().stream()
                .mapToInt(player -> -calculateEarning(player))
                .sum();
    }
}
