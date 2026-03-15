package domain.model;

import java.util.HashMap;
import java.util.Map;

public class Bets {
    private Map<String, Bet> players;

    public Bets() {
        this.players = new HashMap<>();
    }

    public void addBet(Player player, int betAmount) {
        players.put(player.getName(), Bet.of(betAmount));
    }

    public int getBetAmount(Player player) {
        return players.get(player.getName()).getBetAmount();
    }

    public int getFinalMoney(Player player) {
        return players.get(player.getName()).getFinalMoney(player.getPlayerStatus());
    }
}
