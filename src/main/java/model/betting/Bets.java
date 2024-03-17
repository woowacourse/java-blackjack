package model.betting;

import java.util.Collections;
import java.util.Map;
import model.participant.Player;

public class Bets {

    private final Map<String, Bet> bets;

    public Bets(Map<String, Bet> bets) {
        this.bets = Collections.unmodifiableMap(bets);
    }

    public Bet findByPlayer(Player player) {
        return bets.get(player.getName());
    }
}
