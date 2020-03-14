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

    public PlayerResult getPlayerResult(Player player) {
        return gameResults.get(player);
    }

    public Map<Player, PlayerResult> getGameResults() {
        return gameResults;
    }
}
