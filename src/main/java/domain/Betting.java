package domain;

import domain.participant.PlayerName;

import java.util.HashMap;
import java.util.Map;

public class Betting {
    private static final Betting instance = new Betting();

    private final Map<PlayerName, Amount> betting = new HashMap<>();

    private Betting() {}

    public static Betting getInstance() {
        return instance;
    }

    public void setBetting(final PlayerName playerName, final Amount amount) {
        betting.put(playerName, amount);
    }

    public Amount getBetting(final PlayerName playerName) {
        return betting.get(playerName);
    }
}
