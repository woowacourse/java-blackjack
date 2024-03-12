package blackjack.model.result;

import blackjack.model.gamer.Player;
import java.util.HashMap;
import java.util.Map;

public class PlayersResult {

    private final Map<Player, Result> playerResult = new HashMap<>();

    public void addWin(Player player) {
        playerResult.put(player, Result.WIN);
    }

    public void addBlackjack(Player player) {
        playerResult.put(player, Result.BLACKJACK);
    }

    public void addPush(Player player) {
        playerResult.put(player, Result.PUSH);
    }

    public void addLose(Player player) {
        playerResult.put(player, Result.LOSE);
    }

    public Result findPlayerResult(Player player) {
        return playerResult.get(player);
    }
}
