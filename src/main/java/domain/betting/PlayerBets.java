package domain.betting;

import domain.player.Player;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerBets {

    private final Map<Player, Bet> playerBets;

    public PlayerBets(final Map<Player, Bet> playerBets) {
        this.playerBets = new LinkedHashMap<>();
        this.playerBets.putAll(playerBets);
    }

    public Bet get(final Player player) {
        return playerBets.get(player);
    }
}
