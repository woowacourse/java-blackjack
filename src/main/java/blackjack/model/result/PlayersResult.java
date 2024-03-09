package blackjack.model.result;

import blackjack.model.gamer.Player;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlayersResult {

    private final Map<Player, Result> playerResult = new LinkedHashMap<>();

    public void addWin(Player player) {
        playerResult.put(player, Result.WIN);
    }

    public void addLose(Player player) {
        playerResult.put(player, Result.LOSE);
    }

    public void addTie(Player player) {
        playerResult.put(player, Result.TIE);
    }

    public Result findPlayerResult(Player player) {
        return playerResult.get(player);
    }
}
