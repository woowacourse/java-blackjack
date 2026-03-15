package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerBets {
    private final Map<Player, BetAmount> playerBets = new LinkedHashMap<>();

    private PlayerBets() {
    }

    public static PlayerBets of() {
        return new PlayerBets();
    }

    public void add(Player player, BetAmount betAmount) {
        playerBets.put(player, betAmount);
    }

    public BetAmount findBy(Player player) {
        return playerBets.get(player);
    }
}
