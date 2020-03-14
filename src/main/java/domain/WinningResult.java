package domain;

import domain.player.User;
import domain.player.Users;
import domain.player.Player;

import java.util.HashMap;
import java.util.Map;

public class WinningResult {
    private Map<String, Boolean> winningPlayer;

    public WinningResult(Users users) {
        winningPlayer = new HashMap<>();

        User dealer = users.getDealer();
        for (Player player : users.getUsers()) {
            winningPlayer.put(player.getName(), DecisionWinner.compareWinner(player, dealer));
        }
    }

    public Map<String, Boolean> getWinningResult() {
        return winningPlayer;
    }
}
