package domain;

import domain.participant.PlayerName;

import java.util.HashMap;
import java.util.Map;

public class Betting {

    private final Map<PlayerName, BettingAmount> betting = new HashMap<>();

    public Betting() {}

    public void setBetting(final PlayerName playerName, final BettingAmount bettingAmount) {
        betting.put(playerName, bettingAmount);
    }

    public BettingAmount getBetting(final PlayerName playerName) {
        return betting.get(playerName);
    }
}
