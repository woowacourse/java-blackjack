package domain.bet;

import java.util.Collections;
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

    public int getAmountByName(String name) {
        return bettingTable.get(name);
    }

    public List<Integer> getAmountByPlayers() {
        return List.copyOf(bettingTable.values().stream().toList());
    }

    public void clearBet(){
        bettingTable.clear();
    }

    public Map<String, Integer> getBettingTable() {
        return Map.copyOf(bettingTable);
    }
}
