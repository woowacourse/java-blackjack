package blackjack.domain.game;

import blackjack.domain.gamer.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayersGameResult {
    private final Map<Player, PlayerWinStatus> playersResults;

    PlayersGameResult() {
        this.playersResults = new HashMap<>();
    }

    public void put(Player player, PlayerWinStatus playerWinStatus) {
        playersResults.put(player, playerWinStatus);
    }

    public Map<String, PlayerWinStatus> getPlayersNameAndResults() {
        Map<String, PlayerWinStatus> playersNameAndResults = new HashMap<>();

        playersResults.forEach((player, result) ->
                playersNameAndResults.put(player.getName(), result)
        );
        return playersNameAndResults;
    }

    public Map<PlayerWinStatus, Integer> getDealerResult() {
        Map<PlayerWinStatus, Integer> dealerResults = new HashMap<>();
        playersResults.values().forEach(gameResult -> {
                    PlayerWinStatus reversedResult = gameResult.reverse();
                    dealerResults.put(reversedResult, dealerResults.getOrDefault(reversedResult, 0) + 1);
                }
        );
        return dealerResults;
    }
}
