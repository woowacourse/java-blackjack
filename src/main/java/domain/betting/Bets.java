package domain.betting;

import domain.player.PlayerName;
import java.util.LinkedHashMap;
import java.util.Map;

public class Bets {

    private final Map<PlayerName, Bet> playerBets;

    public Bets(final Map<PlayerName, Bet> playerBets) {
        this.playerBets = new LinkedHashMap<>();
        this.playerBets.putAll(playerBets);
    }

    public Bet get(final PlayerName playerName) {
        return playerBets.get(playerName);
    }
}
