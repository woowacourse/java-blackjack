package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class BettingTable {
    private final Map<Player, Earning> moneyTable;

    public BettingTable(Map<Player, Earning> moneyTable) {
        this.moneyTable = new LinkedHashMap<>(moneyTable);
    }

    public static BettingTable from(Map<Player, BetAmount> moneyTable) {
        Map<Player, Earning> moneyMap = new LinkedHashMap<>();
        for (Player player : moneyTable.keySet()) {
            moneyMap.put(player, new Earning(moneyTable.get(player).getAmount()));
        }
        return new BettingTable(moneyMap);
    }

    public void updateProfits(Map<Player, WinningStatus> winningStatusMap) {
        for (Map.Entry<Player, WinningStatus> entry : winningStatusMap.entrySet()) {
            WinningStatus winningStatus = winningStatusMap.get(entry.getKey());
            updateProfit(entry.getKey(), winningStatus);
        }
    }

    public void updateProfit(Player player, WinningStatus winningStatus) {
        Earning initialAmount = moneyTable.get(player);
        Earning earning = initialAmount.calculateProfit(winningStatus);
        moneyTable.put(player, earning);
    }

    public long calculateDealerProfit() {
        long totalPlayerProfit = moneyTable.values().stream()
                .mapToLong(Earning::amount)
                .sum();
        return -totalPlayerProfit;
    }

    public long getPlayerMoneyAmount(Player player) {
        return moneyTable.get(player).amount();
    }

    public Map<Player, Earning> getMoneyTable() {
        return new LinkedHashMap<>(moneyTable);
    }
}