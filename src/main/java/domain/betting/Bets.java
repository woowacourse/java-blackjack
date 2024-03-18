package domain.betting;

import domain.player.PlayerName;
import java.util.LinkedHashMap;
import java.util.Map;

public class Bets {

    private final Map<PlayerName, Money> playerBets;

    public Bets(final Map<PlayerName, Money> playerBets) {
        this.playerBets = new LinkedHashMap<>();
        this.playerBets.putAll(playerBets);
    }

    public Money get(final PlayerName playerName) {
        return playerBets.get(playerName);
    }
}
