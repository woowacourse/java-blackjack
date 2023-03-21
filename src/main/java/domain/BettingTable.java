package domain;

import java.util.Map;

public class BettingTable {

    private final Map<String, BetAmount> bettingTable;

    public BettingTable(Map<String, BetAmount> bettingTable) {
        this.bettingTable = bettingTable;
    }

    public BetAmount getBetAmount(String playerName) {
        return bettingTable.get(playerName);
    }
}
