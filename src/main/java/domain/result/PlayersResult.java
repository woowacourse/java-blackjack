package domain.result;

import domain.Profit;
import domain.WinState;
import domain.gamer.Player;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlayersResult {

    private final Map<Player, Profit> playersProfit;

    public PlayersResult() {
        this.playersProfit = new LinkedHashMap<>();
    }

    public void addProfit(Player player, Profit profit) {
        playersProfit.put(player, profit);
    }

    public void calculateProfit(Player player, WinState winState) {
        winState.calculateProfit(playersProfit.get(player));
        playersProfit.replace(player, playersProfit.get(player));
    }

    public Map<Player, Profit> getPlayersProfit() {
        return Collections.unmodifiableMap(playersProfit);
    }
}
