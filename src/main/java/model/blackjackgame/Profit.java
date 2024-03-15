package model.blackjackgame;

import java.util.HashMap;
import java.util.Map;
import model.player.Player;
import model.player.Players;

public class Profit {
    private final int dealerProfit;
    private final Map<String, Integer> playerProfit;

    public Profit(Bettings bettings) {
        this.dealerProfit = calculateDealerProfit(bettings);
        this.playerProfit = new HashMap<>();
    }

    private int calculateDealerProfit(Bettings bettings) {
        int totalPlayerProfit = bettings.getBettings().stream().mapToInt(Betting::getMoney).sum();
        return -totalPlayerProfit;
    }

    public void createPlayersProfit(Players players, Bettings bettings, Result result) {
        players.getPlayers()
                .forEach(player -> playerProfit.put(player.getName(), calculatePlayerProfit(player, bettings, result)));
    }

    private int calculatePlayerProfit(Player player, Bettings bettings, Result result) {
        return bettings.getBettings().stream()
                .filter(betting -> betting.getPlayer().getName().equals(player.getName()))
                .map(betting -> betting.profit(
                        result.getResult(player))).findFirst().orElse(0);
    }

    public int getDealerProfit() {
        return dealerProfit;
    }

    public Map<String, Integer> getPlayerProfit() {
        return playerProfit;
    }
}
