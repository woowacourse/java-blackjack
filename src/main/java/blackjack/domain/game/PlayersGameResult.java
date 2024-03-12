package blackjack.domain.game;

import blackjack.domain.gamer.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayersGameResult {
    private final Map<Player, PlayerGameResult> playersResults;

    PlayersGameResult() {
        this.playersResults = new HashMap<>();
    }

    public void put(Player player, PlayerGameResult playerGameResult) {
        playersResults.put(player, playerGameResult);
    }

    public Map<Player, PlayerGameResult> getPlayersResults() {
        return playersResults;
    }

    public Map<PlayerGameResult, Integer> getDealerResult() {
        Map<PlayerGameResult, Integer> dealerResults = new HashMap<>();
        playersResults.values().forEach(gameResult -> {
                    PlayerGameResult reversedResult = gameResult.reverse();
                    dealerResults.put(reversedResult, dealerResults.getOrDefault(reversedResult, 0) + 1);
                }
        );
        return dealerResults;
    }
}
