package domain;

import domain.participant.PlayerName;

import java.util.HashMap;
import java.util.Map;

public class Betting {
    private static final Betting instance = new Betting();
    private final Map<PlayerName, Integer> betting = new HashMap<>();

    private Betting() {}

    public static Betting getInstance() {
        return instance;
    }

    public void setBetting(PlayerName playerName, int amount) {
        betting.put(playerName, amount);
    }

    public int getBetting(PlayerName playerName) {
        return betting.get(playerName);
    }
}
