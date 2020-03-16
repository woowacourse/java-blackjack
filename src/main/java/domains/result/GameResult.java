package domains.result;

import domains.user.Dealer;
import domains.user.Player;
import domains.user.Players;

import java.util.HashMap;
import java.util.Map;

public class GameResult {
    private Map<Player, ResultType> playerResult;
    private Map<ResultType, Integer> gameResult;

    public GameResult() {
        this.playerResult = new HashMap<>();
        this.gameResult = new HashMap<>();
    }

    public void create(Players players, Dealer dealer) {
        for (Player player : players) {
            if (checkBurstPlayer(player)) {
                continue;
            }
            playerResult.put(player, player.checkResultType(dealer));
        }
        calculateDealerResult();
    }

    private boolean checkBurstPlayer(Player player) {
        if (player.isBurst()) {
            playerResult.put(player, ResultType.LOSE);
            return true;
        }
        return false;
    }

    public ResultType getWinOrLose(Player player) {
        return playerResult.get(player);
    }

    private void calculateDealerResult() {
        for (ResultType resultType : ResultType.values()) {
            gameResult.put(resultType, 0);
        }

        for (ResultType result : playerResult.values()) {
            gameResult.put(result, gameResult.get(result) + 1);
        }
    }

    public Map<Player, ResultType> getPlayerResult() {
        return playerResult;
    }

    public Map<ResultType, Integer> getGameResult() {
        return gameResult;
    }
}
