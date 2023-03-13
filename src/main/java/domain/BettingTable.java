package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class BettingTable {
    private final Map<Player, Money> bettingTable = new LinkedHashMap<>();

    public void add(Player player, Money money) {
        bettingTable.put(player, money);
    }

    public void calculate(Map<Player, ResultType> playerResult) {
        for (Player player : playerResult.keySet()) {
            ResultType resultType = playerResult.get(player);
            Money money = bettingTable.get(player);
            bettingTable.put(player, resultType.calculateBetting(money));
        }
    }

    public int sum() {
        return bettingTable.values().stream()
                .mapToInt(Money::getMoney)
                .sum();
    }

    public Map<Player, Money> getBettingTable() {
        return bettingTable;
    }

}
