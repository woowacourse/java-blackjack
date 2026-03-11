package domain.result;

import domain.pariticipant.Player;

import java.util.Map;

public class PlayersBettingProfit {
    private final Map<Player, Integer> playersBettingProfit;

    public PlayersBettingProfit(Map<Player, Integer> playersBettingProfit) {
        this.playersBettingProfit = playersBettingProfit;
    }

    public Map<Player, Integer> getPlayersBettingProfit() {
        return Map.copyOf(playersBettingProfit);
    }
}
