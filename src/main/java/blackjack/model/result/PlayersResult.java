package blackjack.model.result;

import blackjack.model.gamer.Player;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlayersResult {

    private final Map<Player, ResultState> playerResult = new LinkedHashMap<>();

    public PlayersResult() {
    }

    public void addWin(Player player) {
        playerResult.put(player, ResultState.WIN);
    }

    public void addLose(Player player) {
        playerResult.put(player, ResultState.LOSE);
    }

    public void addTie(Player player) {
        playerResult.put(player, ResultState.TIE);
    }

    public Map<Player, ResultState> getPlayerResult() {
        return playerResult;
    }
}
