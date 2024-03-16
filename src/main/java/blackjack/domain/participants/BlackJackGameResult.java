package blackjack.domain.participants;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGameResult {

    private final Map<Player, Result> gameResult;

    public BlackJackGameResult(List<Player> players, Dealer dealer) {
        this.gameResult = new LinkedHashMap<>();
        players.forEach(player -> gameResult.put(player, resolveResult(player, dealer)));
    }

    private Result resolveResult(Player player, Dealer dealer) {
        return player.takeOn(dealer);
    }

    public Result getResult(Player player) {
        return gameResult.get(player);
    }

    public Map<Player, Result> getGameResult() {
        return new HashMap<>(gameResult);
    }
}
