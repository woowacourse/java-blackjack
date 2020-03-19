package model.result;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import model.user.Dealer;
import model.user.Player;
import model.user.Players;
import model.user.money.Revenue;

public class GameResult {
    private final Map<Player, Revenue> playersResult = new LinkedHashMap<>();

    public GameResult(final Players players, final Dealer dealer) {
        makePlayersResult(players, dealer);
    }

    private void makePlayersResult(Players players, Dealer dealer) {
        for (Player player : players) {
            Result result = Result.getResult(dealer, player);
            playersResult.put(player, new Revenue(result.calculateRevenue(player)));
        }
    }

    public Map<Player, Revenue> getPlayersResult() {
        return Collections.unmodifiableMap(playersResult);
    }

    public Revenue getDealerResult() {
        return new Revenue(playersResult.values().stream()
            .mapToDouble(Revenue::getRevenue)
            .sum() * -1.0);
    }
}