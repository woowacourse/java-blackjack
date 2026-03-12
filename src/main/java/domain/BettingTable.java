package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class BettingTable {
    private final Map<Player, Money> moneyTable;

    public BettingTable(Map<Player, Money> moneyTable) {
        this.moneyTable = moneyTable;
    }

    public void settleBet(Player player, WinningStatus winningStatus) {
        Money money = new Money(calculateMoney(player, winningStatus));
        moneyTable.put(player, money);
    }

    private long calculateMoney(Player player, WinningStatus winningStatus) {
        return (long) (moneyTable.get(player).getMoney() * winningStatus.getPayoutRatio());
    }

    public long calculateDealerProfit() {
        long totalPlayerProfit = moneyTable.values().stream()
                .mapToLong(Money::getMoney)
                .sum();
        return -totalPlayerProfit;
    }

    public long getPlayerMoneyAmount(Player player) {
        return moneyTable.get(player).getMoney();
    }

    public Map<Player, Money> getMoneyTable() {
        return new LinkedHashMap<>(moneyTable);
    }
}