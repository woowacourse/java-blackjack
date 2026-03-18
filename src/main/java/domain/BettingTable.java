package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class BettingTable {
    private final Map<Player, Money> moneyTable;

    public BettingTable(Map<Player, Money> moneyTable) {
        this.moneyTable = new LinkedHashMap<>(moneyTable);
    }

    public static BettingTable from(Map<Player, BetAmount> moneyTable) {
        Map<Player, Money> moneyMap = new LinkedHashMap<>();
        for (Player player : moneyTable.keySet()) {
            moneyMap.put(player, new Money(moneyTable.get(player).getAmount()));
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
        Money initialAmount = moneyTable.get(player);
        Money profit = initialAmount.calculateProfit(winningStatus);
        moneyTable.put(player, profit);
    }

    public long calculateDealerProfit() {
        long totalPlayerProfit = moneyTable.values().stream()
                .mapToLong(Money::amount)
                .sum();
        return -totalPlayerProfit;
    }

    public long getPlayerMoneyAmount(Player player) {
        return moneyTable.get(player).amount();
    }

    public Map<Player, Money> getMoneyTable() {
        return new LinkedHashMap<>(moneyTable);
    }
}