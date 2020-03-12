package result;

import user.Dealer;
import user.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameResult {
    private Map<Player, WinOrLose> gameResult = new HashMap<>();

    public GameResult(List<Player> players, Dealer dealer) {
        for (Player player : players) {
            gameResult.put(player, WinOrLose.checkWinOrLose(player, dealer));
        }
    }

    public WinOrLose getWinOrLose(Player player) {
        return gameResult.get(player);
    }
}
