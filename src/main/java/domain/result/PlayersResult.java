package domain.result;

import domain.Profit;
import domain.WinState;
import domain.gamer.Player;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlayersResult {

    private final Map<Player, Profit> result;

    public PlayersResult() {
        this.result = new LinkedHashMap<>();
    }

    public void addProfit(Player player, Profit profit) {
        result.put(player, profit);
    }

    public void calculateProfit(Player player, WinState winState) {
        Profit profit = winState.calculateProfit(result.get(player));
        result.replace(player, profit);
    }

    public Map<Player, Profit> getResult() {
        return Collections.unmodifiableMap(result);
    }
}
