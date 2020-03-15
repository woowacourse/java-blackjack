package domains.result;

import domains.user.Dealer;
import domains.user.Player;
import domains.user.Players;

import java.util.HashMap;
import java.util.Map;

public class GameResult {
    private Map<Player, WinOrDrawOrLose> playerResult;
    private Map<WinOrDrawOrLose, Integer> gameResult;

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
            playerResult.put(player, WinOrDrawOrLose.LOSE);
            return true;
        }
        return false;
    }

    public WinOrDrawOrLose checkWinOrLose(Player player, Dealer dealer) {
        if (dealer.isBurst() && !player.isBurst()) {
            return WinOrDrawOrLose.WIN;
        }
        if (player.score() > dealer.score()) {
            return WinOrDrawOrLose.WIN;
        }
        if (player.score() < dealer.score()) {
            return WinOrDrawOrLose.LOSE;
        }
        return WinOrDrawOrLose.DRAW;
    }

    public WinOrDrawOrLose getWinOrLose(Player player) {
        return playerResult.get(player);
    }

    private void calculateDealerResult() {
        for (WinOrDrawOrLose winOrDrawOrLose : WinOrDrawOrLose.values()) {
            gameResult.put(winOrDrawOrLose, 0);
        }

        for (WinOrDrawOrLose result : playerResult.values()) {
            gameResult.put(result, gameResult.get(result) + 1);
        }
    }

    public Map<Player, WinOrDrawOrLose> getPlayerResult() {
        return playerResult;
    }

    public Map<WinOrDrawOrLose, Integer> getGameResult() {
        return gameResult;
    }
}
