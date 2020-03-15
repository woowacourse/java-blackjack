package domains.result;

import domains.user.Dealer;
import domains.user.Player;
import domains.user.Players;

import java.util.HashMap;
import java.util.Map;

public class GameResult {
    private Map<Player, KindOfGameResult> playerResult;
    private Map<KindOfGameResult, Integer> gameResult;

    public GameResult() {
        this.playerResult = new HashMap<>();
        this.gameResult = new HashMap<>();
    }

    public void create(Players players, Dealer dealer) {
        for (Player player : players) {
            if (checkBurstPlayer(player)) {
                continue;
            }
            playerResult.put(player, checkWinOrLose(player, dealer));
        }
        calculateDealerResult();
    }

    private boolean checkBurstPlayer(Player player) {
        if (player.isBurst()) {
            playerResult.put(player, KindOfGameResult.LOSE);
            return true;
        }
        return false;
    }

    public KindOfGameResult checkWinOrLose(Player player, Dealer dealer) {
        if (dealer.isBurst() && !player.isBurst()) {
            return KindOfGameResult.WIN;
        }
        if (player.score() > dealer.score()) {
            return KindOfGameResult.WIN;
        }
        if (player.score() < dealer.score()) {
            return KindOfGameResult.LOSE;
        }
        return KindOfGameResult.DRAW;
    }

    public KindOfGameResult getWinOrLose(Player player) {
        return playerResult.get(player);
    }

    private void calculateDealerResult() {
        for (KindOfGameResult kindOfGameResult : KindOfGameResult.values()) {
            gameResult.put(kindOfGameResult, 0);
        }

        for (KindOfGameResult result : playerResult.values()) {
            gameResult.put(result, gameResult.get(result) + 1);
        }
    }

    public Map<Player, KindOfGameResult> getPlayerResult() {
        return playerResult;
    }

    public Map<KindOfGameResult, Integer> getGameResult() {
        return gameResult;
    }
}
