package domain;

import domain.participant.PlayerName;

import java.util.HashMap;
import java.util.Map;

public class Betting {
    private static final Betting instance = new Betting();

    private final Map<PlayerName, BettingAmount> betting = new HashMap<>();

    private Betting() {}

    public static Betting getInstance() {
        return instance;
    }

    public void setBetting(final PlayerName playerName, final BettingAmount bettingAmount) {
        betting.put(playerName, bettingAmount);
    }

    public BettingAmount getBetting(final PlayerName playerName) {
        return betting.get(playerName);
    }
}
