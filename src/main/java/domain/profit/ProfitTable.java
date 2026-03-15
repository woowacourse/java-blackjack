package domain.profit;

import domain.participant.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ProfitTable {
    private final Map<String, Integer> profitTable;

    public ProfitTable() {
        this.profitTable = new HashMap<>();
    }

    public int findProfitByPlayerName(String name) {
        return profitTable.get(name);
    }

    public void recordProfit(Player player, int profit) {
        profitTable.put(player.getName(), profit);
    }

    public Set<Map.Entry<String, Integer>> findProfitTableEntry() {
        return Set.copyOf(profitTable.entrySet());
    }

    public int dealerCalculateProfit() {
        return profitTable.values().stream()
                .mapToInt(value -> -value)
                .sum();
    }
}
