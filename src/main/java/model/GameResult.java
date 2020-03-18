package model;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {

    private Map<Player, Result> playerResult = new LinkedHashMap<>();

    public GameResult(final Players players, final Dealer dealer) {
        for (Player player : players) {
            Result result = Result.compete(dealer, player);
            playerResult.put(player, result);
        }
    }

    public Map<Player, Result> getPlayerResult() {
        return Collections.unmodifiableMap(playerResult);
    }
}
