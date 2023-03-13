package domain;

import domain.participant.Player;
import java.util.Map;

public class BettingTable {

    private final Map<Player, BetAmount> bettingTable;

    public BettingTable(Map<Player, BetAmount> bettingTable) {
        this.bettingTable = bettingTable;
    }

    public BetAmount getBetAmount(Player player) {
        return bettingTable.get(player);
    }
}
