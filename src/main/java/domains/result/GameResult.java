package domains.result;

import domains.user.Dealer;
import domains.user.Player;
import domains.user.Players;

import java.util.HashMap;
import java.util.Map;

public class GameResult {
    private Map<Player, WinOrLose> playerResult;
    private Map<WinOrLose, Integer> gameResult;

    public GameResult() {
        this.playerResult = new HashMap<>();
        this.gameResult = new HashMap<>();
    }

    public void create(Players players, Dealer dealer) {
        for (Player player : players) {
            if (checkBurstPlayer(player)) {
                continue;
            }
            playerResult.put(player, WinOrLose.checkWinOrLose(player, dealer));
        }
    }

    private boolean checkBurstPlayer(Player player) {
        if (player.isBurst()) {
            playerResult.put(player, WinOrLose.LOSE);
            return true;
        }
        return false;
    }

    public WinOrLose getWinOrLose(Player player) {
        return playerResult.get(player);
    }

    public String calculateDealerResult() {
        for(WinOrLose winOrLose : WinOrLose.values()) {
            gameResult.put(winOrLose, 0);
        }

        for (WinOrLose result : playerResult.values()) {
            gameResult.put(result, gameResult.get(result) + 1);
        }

        return gameResult.get(WinOrLose.LOSE) + WinOrLose.WIN.getWinOrLose()
                + gameResult.get(WinOrLose.DRAW) + WinOrLose.DRAW.getWinOrLose()
                + gameResult.get(WinOrLose.WIN) + WinOrLose.LOSE.getWinOrLose();
    }

    public Map<Player, WinOrLose> getPlayerResult() {
        return playerResult;
    }
}
