package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WinningResult {
    private Map<String, Boolean> winningPlayer;

    public WinningResult(Player dealer, List<Player> users) {
        winningPlayer = new HashMap<>();

        for (Player user : users) {
            User targetUser = (User) user;
            winningPlayer.put(targetUser.getName(), DecisionWinner.compareWinner(targetUser, dealer));
        }
    }

    public Map<String, Boolean> getWinningResult() {
        return winningPlayer;
    }
}
