package domain.bet;

import domain.participant.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BetTable {
    private final Map<String, Integer> bettingTable;

    public BetTable() {
        this.bettingTable = new HashMap<>();
    }

    public void recodeAmount(String name, int amount) {
        bettingTable.put(name, amount);
    }

    public int findBetAmount(Player player) {
        return bettingTable.get(player.getName());
    }
}
