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
        int bettingPrice = betting.get(player);
        if (gameResult == GameResult.WIN){
            betting.put(player, bettingPrice * 2);
        }
        if (gameResult == GameResult.LOSE){
            betting.put(player, 0);
        }
        if (gameResult == GameResult.BLACKJACK){
            betting.put(player, bettingPrice + bettingPrice * 3/2);
        }
    }
}

