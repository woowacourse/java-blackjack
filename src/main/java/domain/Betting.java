package domain;

import domain.participant.PlayerName;

import java.util.Map;

public class Betting {

    private final Map<PlayerName, BettingAmount> betting;

    public Betting(Map<PlayerName, BettingAmount> betting) {
        this.betting = betting;
    }

    public BettingAmount getBetting(final PlayerName playerName) {
        return betting.get(playerName);
    }
}
