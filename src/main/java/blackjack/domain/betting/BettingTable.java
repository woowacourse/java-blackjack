package blackjack.domain.betting;

import blackjack.domain.participant.PlayerName;
import java.util.Map;

public class BettingTable {

    private final Map<PlayerName, Betting> bettingTable;

    public BettingTable(final Map<PlayerName, Betting> bettingTable) {
        this.bettingTable = bettingTable;
    }
}
