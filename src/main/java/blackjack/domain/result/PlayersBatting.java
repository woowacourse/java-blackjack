package blackjack.domain.result;

import blackjack.domain.gamer.Player;

import java.util.LinkedHashMap;
import java.util.Map;

public class PlayersBatting {

    private final Map<Player, Double> playerBatting = new LinkedHashMap<>();

    public PlayersBatting() {
    }

    public void registerPlayerBatting(Player player, double batting) {
        playerBatting.put(player, batting);
    }

    public void calculateBattingReward(Player player, double winningRate) {
        playerBatting.put(player, playerBatting.get(player) * winningRate);
    }

    public Map<Player, Double> getPlayerBatting() {
        return playerBatting;
    }
}
