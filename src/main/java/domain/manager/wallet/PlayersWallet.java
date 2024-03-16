package domain.manager.wallet;

import domain.gamer.Player;
import domain.manager.GameResult;
import domain.manager.Profit;
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

    public double sumAllProfits() {
        return playersProfit.values().stream()
                .mapToDouble(Profit::getValue)
                .sum();
    }
}
