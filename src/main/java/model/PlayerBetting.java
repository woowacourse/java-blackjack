package model;

import java.util.Map;

public class PlayerBetting {
    private final Map<Player, Integer> betting;

    public PlayerBetting(Map<Player, Integer> betting) {
        this.betting = betting;
    }

    public Integer getBettingNet(Player player) {
        return betting.get(player);
    }

    public void calculateBettingResult(Player player, GameResult gameResult) {
        if (gameResult == GameResult.WIN){
            betting.put(player, betting.get(player) * 2);
        }
    }
}

