package domain.manager;

import domain.gamer.Player;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlayersWallet {

    private final Map<Player, Profit> playersProfit;

    public PlayersWallet() {
        this.playersProfit = new LinkedHashMap<>();
    }

    public void addProfit(Player player, Profit profit) {
        playersProfit.put(player, profit);
    }

    public void calculateProfit(Player player, GameResult gameResult) {
        Profit profit = gameResult.calculateProfit(playersProfit.get(player));
        playersProfit.replace(player, profit);
    }

    public Profit findProfitOfPlayer(Player player) {
        return playersProfit.get(player);
    }

    public Map<Player, Profit> getPlayersProfit() {
        return Collections.unmodifiableMap(playersProfit);
    }
}
