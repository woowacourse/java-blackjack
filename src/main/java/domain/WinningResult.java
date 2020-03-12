package domain;

import domain.player.Player;
import domain.player.Players;
import domain.player.User;

import java.util.HashMap;
import java.util.Map;

public class WinningResult {
    private Map<String, Boolean> winningPlayer;

    public WinningResult(Players players) {
        winningPlayer = new HashMap<>();

        Player dealer = players.getDealer();
        for (User user : players.getUsers()) {
            winningPlayer.put(user.getName(), DecisionWinner.compareWinner(user, dealer));
        }
    }

    public Map<String, Boolean> getWinningResult() {
        return winningPlayer;
    }
}
