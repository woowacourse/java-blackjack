package model;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {

    private Map<Player, Profit> playerResult = new LinkedHashMap<>();

    public GameResult(final Players players, final Dealer dealer) {
        for (Player player : players) {
            Result result = Result.compete(dealer, player);
            playerResult.put(player, result.calculateProfit(player));
        }
    }

    public Map<Player, Profit> getPlayerResult() {
        return Collections.unmodifiableMap(playerResult);
    }
}
