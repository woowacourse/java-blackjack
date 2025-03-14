package model.bet;

import java.util.Map;

public class ParticipantsBet {

    private final int dealerBet;
    private final Map<String, Integer> playerBet;

    public ParticipantsBet(final Map<String, Integer> playerBet) {
        dealerBet = 0;
        this.playerBet = playerBet;
    }
}
