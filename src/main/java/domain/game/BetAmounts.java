package domain.game;

import domain.player.Player;

import java.util.HashMap;
import java.util.Map;

public class BetAmounts {
    private final Map<Player, Double> betAmounts;
    
    public BetAmounts() {
        this.betAmounts = new HashMap<>();
    }
    
    public void savePlayerBetAmount(Player player, double betAmount) {
        betAmounts.put(player, betAmount);
    }
    
    public double findBetAmountByPlayer(Player player) {
        return betAmounts.get(player);
    }
}
